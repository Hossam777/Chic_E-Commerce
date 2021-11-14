package com.app.chic_ecommerce.shopproductsfragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.FragmentShopProductsBinding
import com.app.chic_ecommerce.shopproductscategoryfragment.presentation.ShopProductsCategoryFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ShopProductsFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewmodel: ShopProductsFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentShopProductsBinding
    private lateinit var shopListCategoriesRecyclerAdapter: ShopListCategoriesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.ShopProductsFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_products, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this

        viewmodel.getCategories()
        subscribeOnError()
        subscribeOnCategories()
        setupAdapter()
        return binding.root
    }

    private fun subscribeOnCategories() {
        viewmodel.categories.observe(viewLifecycleOwner, {
            shopListCategoriesRecyclerAdapter.setCategories(it)
        })
    }

    private fun subscribeOnError() {
        viewmodel.onError.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden)
            session.currentFragment.postValue(FragmentsEnum.ShopProductsFragment)
    }

    private fun setupAdapter() {
        shopListCategoriesRecyclerAdapter = ShopListCategoriesRecyclerAdapter{
            activity?.supportFragmentManager?.commit {
                val arguments = Bundle()
                arguments.putString("category", it)
                val fragment = ShopProductsCategoryFragment()
                fragment.arguments = arguments
                add(R.id.shopProductsFragment, fragment, "ShopProductsCategoryFragment")
                addToBackStack("ShopProductsCategoryFragment")
            }
        }
        binding.shopProductsCategoriesRecycler.adapter = shopListCategoriesRecyclerAdapter
        binding.shopProductsCategoriesRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}