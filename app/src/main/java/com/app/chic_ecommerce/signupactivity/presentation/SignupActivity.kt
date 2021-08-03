package com.app.chic_ecommerce.signupactivity.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.User
import com.app.chic_ecommerce.databinding.ActivitySignupBinding
import com.app.chic_ecommerce.loginactivity.presentation.LoginActivity
import com.app.chic_ecommerce.navigationactivity.presentation.NavigationActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: SignupActivityViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        subscribeOnError()
    }

    private fun subscribeOnError() {
        viewModel.onError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun login(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun back(view: View) {
        finish()
    }

    fun signup(view: View) {
        if(viewModel.signup()){
            session.user.postValue(User(viewModel.username.value!!, viewModel.mail.value!!, viewModel.password.value!!))
            startActivity(Intent(this, NavigationActivity::class.java))
        }
    }
}