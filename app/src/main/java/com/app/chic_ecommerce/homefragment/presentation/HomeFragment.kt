package com.app.chic_ecommerce.homefragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.CartProduct
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.common.data.entities.SliderItemModel
import com.app.chic_ecommerce.common.presentation.SliderPagerAdapter
import com.app.chic_ecommerce.databinding.FragmentHomeBinding
import com.app.chic_ecommerce.shopproductscategoryfragment.presentation.ShopProductsCategoryFragment
import com.app.chic_ecommerce.wishlistfragment.presentation.WishlistFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: HomeFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.HomeFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        onClick()
        setupView()
        return binding.root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden)
            session.currentFragment.postValue(FragmentsEnum.HomeFragment)
    }

    private fun setupView() {
        binding.shopBtn.setOnClickListener {
            Toast.makeText(context, "Sale Clicked", Toast.LENGTH_SHORT).show()
        }
        val sliderItems: MutableList<SliderItemModel> = mutableListOf()
        sliderItems.add(SliderItemModel(
            R.drawable.girl_shadow,
            "SEASON SALE up to 25%"){
            Toast.makeText(context, "25% Clicked", Toast.LENGTH_SHORT).show()
        })
        sliderItems.add(SliderItemModel(
            R.drawable.girl_shadow,
            "SEASON SALE up to 35%"){
            Toast.makeText(context, "35% Clicked", Toast.LENGTH_SHORT).show()
        })
        sliderItems.add(SliderItemModel(
            R.drawable.girl_shadow,
            "SEASON SALE up to 50%"){
            Toast.makeText(context, "50% Clicked", Toast.LENGTH_SHORT).show()
        })
        sliderItems.add(SliderItemModel(
            R.drawable.girl_shadow,
            "SEASON SALE up to 70%"){
            Toast.makeText(context, "70% Clicked", Toast.LENGTH_SHORT).show()
        })
        val sliderPagerAdapter = SliderPagerAdapter(context, sliderItems, false)
        binding.viewPager.adapter = sliderPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }

    private fun onClick() {
        binding.saleShop.setOnClickListener{ openShopCategoryFragment("Sale%") }
        binding.saleShopLayout.setOnClickListener{ openShopCategoryFragment("Sale%") }
        binding.shopBtn.setOnClickListener{ openShopCategoryFragment("Sale%") }
        binding.cashmereSilkShop.setOnClickListener{ openShopCategoryFragment("Cashmere & Silk") }
        binding.leatherBagsShop.setOnClickListener{ openShopCategoryFragment("Leather Bags") }
        binding.exploreDressesShop.setOnClickListener{ openShopCategoryFragment("Dresses") }
    }

    private fun openShopCategoryFragment(category: String){
        activity?.supportFragmentManager?.commit {
            val arguments = Bundle()
            arguments.putString("category", category)
            val fragment = ShopProductsCategoryFragment()
            fragment.arguments = arguments
            add(R.id.homeFragment, fragment, "ShopProductsCategoryFragment")
            addToBackStack("ShopProductsCategoryFragment")
        }
    }

}