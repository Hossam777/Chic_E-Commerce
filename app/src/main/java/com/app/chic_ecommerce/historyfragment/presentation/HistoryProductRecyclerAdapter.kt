package com.app.chic_ecommerce.historyfragment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.databinding.RecyclerHistoryProductBinding
import com.app.chic_ecommerce.historyfragment.data.entities.OrderProduct
import com.squareup.picasso.Picasso

class HistoryProductRecyclerAdapter():
    RecyclerView.Adapter<HistoryProductRecyclerAdapter.HistoryProductRecyclerHolder>() {

    private var productList: List<OrderProduct> = mutableListOf()

    fun setProductList(productList: List<OrderProduct>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryProductRecyclerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerHistoryProductBinding.inflate(inflate, parent, false)
        return HistoryProductRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryProductRecyclerHolder, position: Int) {
        Picasso.get().load(productList[position].productimg).into(holder.binding.productImg)
        holder.binding.productName.text = productList[position].productname
        holder.binding.productSize.text = productList[position].size
        holder.binding.productColor.text = productList[position].color
        holder.binding.productQuantity.text = productList[position].quantity.toString()
        holder.binding.productPrice.text = "$" + productList[position].price
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class HistoryProductRecyclerHolder(
        val binding: RecyclerHistoryProductBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}