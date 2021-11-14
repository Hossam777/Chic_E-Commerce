package com.app.chic_ecommerce.mainactivity.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.databinding.ActivityMainBinding
import com.app.chic_ecommerce.loginactivity.presentation.LoginActivity
import com.app.chic_ecommerce.navigationactivity.presentation.NavigationActivity
import com.app.chic_ecommerce.signupactivity.presentation.SignupActivity
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: MainActivityViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        session.setActivity(this)
        session.loadToken()
        session.loadWishlist()
        session.loadCart()
        subscribeOnToken()
        subscribeOnUser()
        subscribeOnError()
    }

    private fun subscribeOnToken() {
        session.token.observe(this, {
            viewModel.checkToken(it)
        })
    }

    private fun subscribeOnUser() {
        viewModel.user.observe(this, {
            session.user.postValue(it)
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        })
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
    fun signup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }
}