package com.app.chic_ecommerce.cartfragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.FragmentCartBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CartFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: CartFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.CartFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.viewmodel = viewModel
        binding.session = session
        binding.lifecycleOwner = this

        setupAdapter()
        subscribeOnCart()
        subscribeOnPromoCode()
        return binding.root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden)
            session.currentFragment.postValue(FragmentsEnum.CartFragment)
    }

    private fun setupAdapter() {
        cartAdapter = CartRecyclerAdapter({
            session.removeCartProduct(it)
        }, {
            session.addCartProductQuantity(it)
        }, {
            session.removeCartProductQuantity(it)
        })
        binding.cartRecycler.adapter = cartAdapter
        binding.cartRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun subscribeOnCart() {
        session.cart.observe(viewLifecycleOwner, { list ->
            if(session.cart.value != null){
                cartAdapter.setCartList(list)
                var totalPrice = 0.0
                list.forEach {
                    totalPrice += it.quantity * it.price
                }
                binding.totalPriceTxt.text = "$" + totalPrice
            }
        })
    }
    private fun subscribeOnPromoCode() {
        viewModel.promoCode.observe(viewLifecycleOwner, {
            if (it == "")
                binding.promoCodeValidationMessage.text = ""
            else
                binding.promoCodeValidationMessage.text = "PromoCode is Invalid"
        })
    }
}