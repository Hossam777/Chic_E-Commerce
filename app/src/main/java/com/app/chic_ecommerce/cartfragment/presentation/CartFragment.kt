package com.app.chic_ecommerce.cartfragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.FragmentCartBinding
import com.app.chic_ecommerce.databinding.FragmentProfileBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CartFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: CartFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.CartFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.viewmodel = viewModel
        binding.session = session
        binding.lifecycleOwner = this

        subscribeOnSession()
        return binding.root
    }

    private fun subscribeOnSession() {
        session.cart.observe(viewLifecycleOwner, {
            if(session.cart.value != null){
                //add products to recycler
            }
        })
    }
}