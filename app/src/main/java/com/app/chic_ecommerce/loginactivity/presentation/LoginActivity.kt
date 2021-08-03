package com.app.chic_ecommerce.loginactivity.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.databinding.ActivityLoginBinding
import com.app.chic_ecommerce.signupactivity.presentation.SignupActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: LoginActivityViewModel by instance()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    fun signup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }

    fun login(view: View) {
        Toast.makeText(this, "Service not completed yet please sign up", Toast.LENGTH_SHORT).show()
    }
}