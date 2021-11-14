package com.app.chic_ecommerce.wishlistfragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.common.data.mockup.products
import com.app.chic_ecommerce.databinding.FragmentWishlistBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class WishlistFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewmodel: WishlistFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentWishlistBinding
    private lateinit var wishlistAdapter: WishlistRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.WishlistFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wishlist, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this

        setupAdapter()
        subscribeOnSession()
        return binding.root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden)
            session.currentFragment.postValue(FragmentsEnum.WishlistFragment)
    }

    private fun setupAdapter() {
        wishlistAdapter = WishlistRecyclerAdapter({
            TODO("RemoveFromWishlist")
        }, {
            TODO("AddToCart")
        })
        wishlistAdapter.setWishlist(products)
        binding.wishlistRecycler.adapter = wishlistAdapter
        binding.wishlistRecycler.layoutManager = GridLayoutManager(context, 2)
    }

    private fun subscribeOnSession() {
        session.wishlist.observe(viewLifecycleOwner, {
            if(session.cart.value != null){
                wishlistAdapter.setWishlist(it)
            }
        })
    }
}