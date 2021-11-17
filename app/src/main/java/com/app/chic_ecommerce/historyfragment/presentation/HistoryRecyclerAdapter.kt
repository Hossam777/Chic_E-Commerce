package com.app.chic_ecommerce.historyfragment.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.databinding.RecyclerHistoryBinding
import com.app.chic_ecommerce.historyfragment.data.entities.Order

class HistoryRecyclerAdapter(private val context: Context?):
    RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryRecyclerHolder>() {

    private var orders: List<Order> = mutableListOf()

    fun setOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryRecyclerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerHistoryBinding.inflate(inflate, parent, false)
        return HistoryRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryRecyclerHolder, position: Int) {
        holder.binding.orderAddressTxt.text = orders[position].header.address
        holder.binding.orderTotalPriceTxt.text = "$" + orders[position].header.totalprice
        holder.binding.orderDateTxt.text = orders[position].header.created_at
        val ordersRecycler = HistoryProductRecyclerAdapter()
        holder.binding.orderProductsRecycler.adapter = ordersRecycler
        holder.binding.orderProductsRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        ordersRecycler.setProductList(orders[position].products)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    inner class HistoryRecyclerHolder(
        val binding: RecyclerHistoryBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}