package com.app.chic_ecommerce.historyfragment.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.Session
import com.app.chic_ecommerce.common.data.entities.FragmentsEnum
import com.app.chic_ecommerce.databinding.FragmentHistoryBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HistoryFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: HistoryFragmentViewModel by instance()
    private val session: Session by instance()
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyRecycler: HistoryRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session.currentFragment.postValue(FragmentsEnum.HistoryFragment)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        setupAdapter()
        subscribeOnOrders()
        subscribeOnLoading()
        subscribeOnError()
        return binding.root
    }

    private fun setupAdapter() {
        historyRecycler = HistoryRecyclerAdapter(context)
        binding.historyRecycler.adapter = historyRecycler
        binding.historyRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun subscribeOnOrders() {
        viewModel.orders.observe(viewLifecycleOwner, {
            historyRecycler.setOrders(it)
        })
    }

    private fun subscribeOnLoading() {
        viewModel.onLoading.observe(viewLifecycleOwner, {
            if(it){
                binding.loadingLayout.visibility = View.VISIBLE
            }else{
                binding.loadingLayout.visibility = View.INVISIBLE
            }
        })
    }

    private fun subscribeOnError() {
        viewModel.onError.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden){
            session.currentFragment.postValue(FragmentsEnum.HistoryFragment)
            viewModel.getOrders(session.token.value!!)
        }
    }
}