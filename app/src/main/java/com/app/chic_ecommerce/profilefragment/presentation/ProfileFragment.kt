package com.app.chic_ecommerce.profilefragment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.FragmentProfileBinding
import com.app.chic_ecommerce.wishlistfragment.presentation.WishlistFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: ProfileFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.viewmodel = viewModel
        binding.session = session
        binding.lifecycleOwner = this

        subscribeOnSession()
        subscribeOnOpenWishlist()
        subscribeOnEditAccount()
        return binding.root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden)
            session.currentFragment.postValue(FragmentsEnum.ProfileFragment)
    }

    private fun subscribeOnOpenWishlist() {
        binding.wishlistBtn.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                add(R.id.profileFragment, WishlistFragment(), "WishlistFragment")
                addToBackStack("WishlistFragment")
            }
        }
    }

    private fun subscribeOnEditAccount() {
        binding.editAccount.setOnClickListener {
            Toast.makeText(context, "SSSSSSSSSSSSSSSSSSSSSh", Toast.LENGTH_SHORT).show()
            println("OnEditAccount")
        }
    }

    private fun subscribeOnSession() {
        session.wishlist.observe(viewLifecycleOwner, {
            if(session.wishlist.value != null){
                binding.wishlistBtn.text = "WISHLIST(" + session.wishlist.value!!.size.toString() + ")"
            }else{
                binding.wishlistBtn.text = "WISHLIST(0)"
            }
        })
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}