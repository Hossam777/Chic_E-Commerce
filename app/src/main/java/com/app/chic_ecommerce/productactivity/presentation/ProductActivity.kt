package com.app.chic_ecommerce.productactivity.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.Color
import com.app.chic_ecommerce.common.data.entities.SliderItemModel
import com.app.chic_ecommerce.common.presentation.SliderPagerAdapter
import com.app.chic_ecommerce.databinding.ActivityProductBinding
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

        setupView()
        subscribeOnCart()
        subscribeOnFocusedProduct()
    }

    private fun setupView() {
        binding.addToCartBtn.setOnClickListener {
            TODO("Add Product To Cart")
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.favBtn.setOnClickListener {
            if(session.findInWishlist(session.focusedProduct.value!!) == -1){
                binding.favBtn.setImageResource(R.mipmap.heart_filled)
                session.addWishlistProduct(session.focusedProduct.value!!)
            }else{
                binding.favBtn.setImageResource(R.mipmap.heart)
                session.removeWishlistProduct(session.focusedProduct.value!!)
            }
        }

    }

    private fun subscribeOnFocusedProduct() {
        session.focusedProduct.observe(this, { product ->
            if(session.findInWishlist(product) != -1){
                binding.favBtn.setImageResource(R.mipmap.heart_filled)
            }
            binding.productName.text = product.name
            binding.productPrice.text = product.price.toString()
            binding.descriptionTxt.text = product.description
            //slider adapter
            val sizes: MutableList<String> = mutableListOf()
            product.sizes.split(";").forEach {
                sizes.add(it)
            }
            val adapter = ArrayAdapter(this, R.layout.sizes_spinner_item, sizes)
            binding.sizesSpinner.adapter = adapter
            val sliderItems: MutableList<SliderItemModel> = mutableListOf()
            sliderItems.add(SliderItemModel(
                product.image1,
                ""))
            sliderItems.add(SliderItemModel(
                product.image2,
                ""))
            sliderItems.add(SliderItemModel(
                product.image3,
                ""))
            val sliderPagerAdapter = SliderPagerAdapter(this, sliderItems, true)
            binding.viewPager.adapter = sliderPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager, true)
            //colors adapter
            val colors: MutableList<Color> = mutableListOf()
            product.colors.split(";").forEach {
                if(it.isNotEmpty())
                    colors.add(Color(it.split(":")[0], it.split(":")[1]))
            }
            colorsRecyclerAdapter = ColorsRecyclerAdapter(this, resources){
                viewModel.selectedColor.postValue(it.name)
            }
            colorsRecyclerAdapter.setColors(colors)
            binding.colorsRecycler.adapter = colorsRecyclerAdapter
            binding.colorsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        })
    }

    private fun subscribeOnCart() {
        session.cart.observe(this, {
            binding.cartCounter.text = it.size.toString()
        })
    }
}