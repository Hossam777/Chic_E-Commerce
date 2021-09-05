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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.cartfragment.presentation.CartFragment
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.ActivityNavigationBinding
import com.app.chic_ecommerce.homefragment.presentation.HomeFragment
import com.app.chic_ecommerce.profilefragment.presentation.ProfileFragment
import com.app.chic_ecommerce.shopproductsfragment.presentation.ShopProductsFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.lang.Exception

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

        supportFragmentManager.commit {
            add<CartFragment>(R.id.fragmentContainer, "CartFragment")
            add<ProfileFragment>(R.id.fragmentContainer, "ProfileFragment")
            add<ShopProductsFragment>(R.id.fragmentContainer, "ShopProductsFragment")
            add<HomeFragment>(R.id.fragmentContainer, "HomeFragment")
        }
        setupNavigation()
        subscribeOnFragmentChanged()
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.commit {
            hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
            hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
            hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
        }
    }

    private fun subscribeOnFragmentChanged() {
        session.currentFragment.observe(this, {
            println(it.toString())
            when (session.currentFragment.value){
                FragmentsEnum.ProfileFragment -> {

                }
                FragmentsEnum.WishlistFragment -> {

                }
                FragmentsEnum.CartFragment -> {

                }
                FragmentsEnum.HomeFragment -> {

                }
                FragmentsEnum.ShopProductsFragment -> {

                }
            }
        })
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
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        show(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_shop_products -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        show(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_my_account -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        show(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_my_cart -> {
                    supportFragmentManager.commit {
                        show(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_settings -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_about -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
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
            TODO("CartBtn")
        }
    }
}