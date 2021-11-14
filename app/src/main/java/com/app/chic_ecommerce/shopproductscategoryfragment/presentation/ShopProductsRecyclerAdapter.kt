package com.app.chic_ecommerce.shopproductscategoryfragment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.databinding.RecyclerShopProductsBinding
import com.squareup.picasso.Picasso

class ShopProductsRecyclerAdapter(private val onClick: (product: Product) -> Unit):
    RecyclerView.Adapter<ShopProductsRecyclerAdapter.ShopProductsRecyclerHolder>() {
    private var shopList: MutableList<Product> = mutableListOf()
    fun setShopList(shopList: MutableList<Product>) {
        this.shopList = shopList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductsRecyclerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerShopProductsBinding.inflate(inflate, parent, false)
        return ShopProductsRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopProductsRecyclerHolder, position: Int) {
        Picasso.get().load(shopList[position].image1).into(holder.binding.productImg)
        holder.binding.shopProduct.setOnClickListener { onClick(shopList[position]) }
        holder.binding.productName.text = shopList[position].name
        holder.binding.productPrice.text = "$" + shopList[position].price
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    inner class ShopProductsRecyclerHolder(
        val binding: RecyclerShopProductsBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}