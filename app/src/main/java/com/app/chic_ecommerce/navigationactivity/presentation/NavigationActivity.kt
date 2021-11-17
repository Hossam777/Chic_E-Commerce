package com.app.chic_ecommerce.navigationactivity.presentation

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.cartfragment.presentation.CartFragment
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.ActivityNavigationBinding
import com.app.chic_ecommerce.historyfragment.presentation.HistoryFragment
import com.app.chic_ecommerce.homefragment.presentation.HomeFragment
import com.app.chic_ecommerce.profilefragment.presentation.ProfileFragment
import com.app.chic_ecommerce.shopproductsfragment.presentation.ShopProductsFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import kotlin.system.exitProcess

class NavigationActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: NavigationActivityViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: ActivityNavigationBinding
    private var firstOpen: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
        binding.viewmodel = viewModel
        binding.session = session
        binding.lifecycleOwner = this

        supportFragmentManager.commit {
            add<HistoryFragment>(R.id.fragmentContainer, "HistoryFragment")
            add<CartFragment>(R.id.fragmentContainer, "CartFragment")
            add<ProfileFragment>(R.id.fragmentContainer, "ProfileFragment")
            add<ShopProductsFragment>(R.id.fragmentContainer, "ShopProductsFragment")
            add<HomeFragment>(R.id.fragmentContainer, "HomeFragment")
        }
        setupNavigation()
        subscribeOnFragmentChanged()
        subscribeOnCartChanged()
    }

    private fun subscribeOnCartChanged() {
        session.cart.observe(this, {
            binding.cartCounter.text = it.size.toString()
            if(session.currentFragment.value!! == FragmentsEnum.CartFragment)
                binding.navigationActivityTitle.text = resources.getText(R.string.your_cart).toString() + "(" + session.cart.value!!.size + ")"
        })
    }

    override fun onDestroy() {
        exitProcess(0)
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        if(firstOpen){
            supportFragmentManager.commit {
                hide(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
                hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
            }
            firstOpen = false
        }
    }

    private fun subscribeOnFragmentChanged() {
        session.currentFragment.observe(this, {
            when (session.currentFragment.value){
                FragmentsEnum.ProfileFragment -> {
                    binding.navigationActivityTitle.text = resources.getText(R.string.my_account)
                    binding.cartBtn.visibility = View.INVISIBLE
                    binding.cartCounter.visibility = View.INVISIBLE
                }
                FragmentsEnum.WishlistFragment -> {
                    binding.navigationActivityTitle.text = resources.getText(R.string.wishlist)
                    binding.cartBtn.visibility = View.VISIBLE
                    binding.cartCounter.visibility = View.VISIBLE
                }
                FragmentsEnum.CartFragment -> {
                    binding.navigationActivityTitle.text = resources.getText(R.string.your_cart).toString() + "(" + session.cart.value!!.size + ")"
                    binding.cartBtn.visibility = View.INVISIBLE
                    binding.cartCounter.visibility = View.INVISIBLE
                }
                FragmentsEnum.HomeFragment -> {
                    binding.navigationActivityTitle.text = resources.getText(R.string.minimal_chic)
                    binding.cartBtn.visibility = View.VISIBLE
                    binding.cartCounter.visibility = View.VISIBLE
                }
                FragmentsEnum.ShopProductsFragment -> {
                    binding.navigationActivityTitle.text = resources.getText(R.string.minimal_chic)
                    binding.cartBtn.visibility = View.VISIBLE
                    binding.cartCounter.visibility = View.VISIBLE
                }
                FragmentsEnum.ShopProductsCategoryFragment -> {
                    binding.navigationActivityTitle.text = resources.getText(R.string.minimal_chic)
                    binding.cartBtn.visibility = View.VISIBLE
                    binding.cartCounter.visibility = View.VISIBLE
                }
                FragmentsEnum.HistoryFragment -> {
                    binding.navigationActivityTitle.text = resources.getText(R.string.history)
                    binding.cartBtn.visibility = View.VISIBLE
                    binding.cartCounter.visibility = View.VISIBLE
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
                        hide(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        show(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_shop_products -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        show(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_my_account -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        show(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_my_cart -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
                        show(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_history -> {
                    supportFragmentManager.commit {
                        show(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                        hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
                    }
                }
                R.id.menu_about -> {
                    supportFragmentManager.commit {
                        hide(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
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
                Toast.makeText(this, textView.text.toString(), Toast.LENGTH_SHORT).show()
                true
            }
            false
        }
        binding.cartBtn.setOnClickListener {
            binding.navigationView.setCheckedItem(R.id.menu_my_cart)
            supportFragmentManager.commit {
                hide(supportFragmentManager.findFragmentByTag("HistoryFragment")!!)
                show(supportFragmentManager.findFragmentByTag("CartFragment")!!)
                hide(supportFragmentManager.findFragmentByTag("ProfileFragment")!!)
                hide(supportFragmentManager.findFragmentByTag("ShopProductsFragment")!!)
                hide(supportFragmentManager.findFragmentByTag("HomeFragment")!!)
            }
        }
    }
}