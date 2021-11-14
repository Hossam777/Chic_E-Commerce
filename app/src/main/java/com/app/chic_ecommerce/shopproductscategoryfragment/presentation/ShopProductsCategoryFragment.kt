package com.app.chic_ecommerce.shopproductscategoryfragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.common.data.mockup.products
import com.app.chic_ecommerce.databinding.FragmentShopProductsCategoryBinding
import com.project.ecommerce.shopfragmentlayer3.presentation.adapters.CategorySectionsRecyclerAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ShopProductsCategoryFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewmodel: ShopProductsCategoryViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentShopProductsCategoryBinding
    private lateinit var subCategoriesRecycler: CategorySectionsRecyclerAdapter
    private lateinit var shopProductsAdapter: ShopProductsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.ShopProductsCategoryFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_products_category, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.category.postValue(arguments?.getString("category")!!)

        viewmodel.getSubCategories()
        subscribeOnError()
        subscribeOnSubCategories()
        setupSectionsRecycler()
        setupShopProductsRecycler()
        return binding.root
    }

    private fun subscribeOnSubCategories() {
        viewmodel.subCategory.observe(viewLifecycleOwner, {
            subCategoriesRecycler.setSections(it)
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
            session.currentFragment.postValue(FragmentsEnum.ShopProductsCategoryFragment)
    }

    private fun setupShopProductsRecycler() {
        shopProductsAdapter = ShopProductsRecyclerAdapter(){
            TODO("open Product")
        }
        shopProductsAdapter.setShopList(products)
        binding.shopListRecycler.adapter = shopProductsAdapter
        binding.shopListRecycler.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setupSectionsRecycler() {
        subCategoriesRecycler = CategorySectionsRecyclerAdapter (resources, {

        },{

        })
        binding.sectionsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.sectionsRecycler.adapter = subCategoriesRecycler
    }

}