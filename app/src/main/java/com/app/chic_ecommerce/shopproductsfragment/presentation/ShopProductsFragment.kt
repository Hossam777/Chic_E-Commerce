package com.app.chic_ecommerce.shopproductsfragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.FragmentShopProductsBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ShopProductsFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewmodel: ShopProductsFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentShopProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.ShopProductsFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_products, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this

        return binding.root
    }
}