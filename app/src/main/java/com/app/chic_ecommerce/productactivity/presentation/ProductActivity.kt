package com.app.chic_ecommerce.productactivity.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.SliderItemModel
import com.app.chic_ecommerce.common.data.mockup.categories
import com.app.chic_ecommerce.common.data.mockup.colors
import com.app.chic_ecommerce.common.presentation.SliderPagerAdapter
import com.app.chic_ecommerce.databinding.ActivityProductBinding
import com.app.chic_ecommerce.shopproductscategoryfragment.presentation.ShopProductsCategoryFragment
import com.app.chic_ecommerce.shopproductsfragment.presentation.ShopListCategoriesRecyclerAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ProductActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: ProductActivityViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: ActivityProductBinding
    private lateinit var colorsRecyclerAdapter: ColorsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)
        binding.viewmodel = viewModel
        binding.session = session
        binding.lifecycleOwner = this

        //intent.getBundleExtra("")

        setupView()
        subscribeOnSession()
        setupAdapter()
    }

    private fun setupView() {
        binding.addToCartBtn.setOnClickListener {
            Toast.makeText(this, "Add To Cart", Toast.LENGTH_SHORT).show()
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.favBtn.setOnClickListener {
            Toast.makeText(this, "Fav Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.productName.text = "Wool Blend Cream Coat"
        binding.productPrice.text = "$134"
        binding.descriptionTxt.text = "Perfect autumn wool coat, created by Italian brand Woolissima, will make you feel warm and look chic."
        val sizes: MutableList<String> = mutableListOf(
            "S",
            "M",
            "X",
            "2X",
        )
        val adapter = ArrayAdapter(this, R.layout.sizes_spinner_item, sizes)
        binding.sizesSpinner.adapter = adapter
        val sliderItems: MutableList<SliderItemModel> = mutableListOf()
        sliderItems.add(SliderItemModel(
            "https://i.pinimg.com/564x/c3/dc/38/c3dc38a87605e7d1b99a53bbc336e829.jpg",
            ""))
        sliderItems.add(SliderItemModel(
            "https://i.pinimg.com/236x/86/8a/05/868a051c9b452576e18514e5ef83060b.jpg",
            ""))
        sliderItems.add(SliderItemModel(
            "https://i.pinimg.com/236x/d6/6a/33/d66a338b05d71ca13e1367aa1ac1bf74.jpg",
            ""))
        sliderItems.add(SliderItemModel(
            "https://i.pinimg.com/236x/7f/c6/6d/7fc66da657be0077579d890213ebfa64.jpg",
            ""))
        val sliderPagerAdapter = SliderPagerAdapter(this, sliderItems, true)
        binding.viewPager.adapter = sliderPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }

    private fun subscribeOnSession() {
        session.cart.observe(this, {

        })
    }

    private fun setupAdapter() {
        colorsRecyclerAdapter = ColorsRecyclerAdapter(this, resources){

        }
        colorsRecyclerAdapter.setColors(colors)
        binding.colorsRecycler.adapter = colorsRecyclerAdapter
        binding.colorsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}