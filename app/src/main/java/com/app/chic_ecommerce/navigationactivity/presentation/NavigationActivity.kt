package com.app.chic_ecommerce.navigationactivity.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.databinding.ActivityNavigationBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class NavigationActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: NavigationActivityViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        binding.viewmodel = viewModel
        binding.session = session
        binding.lifecycleOwner = this

        setupNavigation()
    }

    private fun setupNavigation() {
        binding.menuBtn.setOnClickListener {
            if(!binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }else{
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home_page -> {

                }
                R.id.menu_shop_products -> {

                }
                R.id.menu_my_account -> {

                }
                R.id.menu_my_cart -> {

                }
                R.id.menu_settings -> {

                }
                R.id.menu_about -> {

                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        binding.navigationView.getHeaderView(0).findViewById<EditText>(R.id.searchBox).setOnEditorActionListener {
                textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                true
            }
            false
        }
        binding.cartBtn.setOnClickListener {
            
        }
    }
}