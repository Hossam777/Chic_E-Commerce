package com.app.chic_ecommerce.loginactivity.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.User
import com.app.chic_ecommerce.databinding.ActivityLoginBinding
import com.app.chic_ecommerce.mainactivity.presentation.MainActivity
import com.app.chic_ecommerce.navigationactivity.presentation.NavigationActivity
import com.app.chic_ecommerce.signupactivity.presentation.SignupActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: LoginActivityViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        subscribeOnError()
        subscribeOnLoggedIn()
    }

    private fun subscribeOnLoggedIn() {
        viewModel.onLoggedIn.observe(this, {
            session.saveToken(viewModel.token.value!!)
            session.user.postValue(User(viewModel.user.value?.username!!, viewModel.user.value?.mail!!, viewModel.user.value?.phone!!))
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        })
    }

    private fun subscribeOnError() {
        viewModel.onError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun signup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }

    fun login(view: View) {
        if(viewModel.validateLogin()){
            viewModel.login()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}