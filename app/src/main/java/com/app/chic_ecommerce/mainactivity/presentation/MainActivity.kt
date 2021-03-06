package com.app.chic_ecommerce.mainactivity.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.databinding.ActivityMainBinding
import com.app.chic_ecommerce.loginactivity.presentation.LoginActivity
import com.app.chic_ecommerce.navigationactivity.presentation.NavigationActivity
import com.app.chic_ecommerce.signupactivity.presentation.SignupActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
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
        subscribeOnLoading()
        subscribeOnError()
    }

    private fun subscribeOnLoading() {
        viewModel.onLoading.observe(this, {
            if(it){
                binding.loadingLayout.visibility = View.VISIBLE
            }else{
                binding.loadingLayout.visibility = View.INVISIBLE
            }
        })
    }

    private fun subscribeOnToken() {
        session.token.observe(this, {
            if(it.length > 10)
                viewModel.checkToken(it)
        })
    }

    private fun subscribeOnUser() {
        viewModel.user.observe(this, {
            session.user.postValue(it)
            startActivity(Intent(this, NavigationActivity::class.java))
        })
    }

    private fun subscribeOnError() {
        viewModel.onError.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun login(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
    fun signup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
    }
}