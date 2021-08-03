package com.app.chic_ecommerce.mainactivity.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.databinding.ActivityMainBinding
import com.app.chic_ecommerce.loginactivity.presentation.LoginActivity
import com.app.chic_ecommerce.signupactivity.presentation.SignupActivity
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: MainActivityViewModel by instance()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    fun login(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
    fun signup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
    }
}