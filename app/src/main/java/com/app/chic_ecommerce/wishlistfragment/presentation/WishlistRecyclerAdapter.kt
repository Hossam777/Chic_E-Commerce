package com.app.chic_ecommerce.wishlistfragment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.databinding.RecyclerWishlistBinding
import com.squareup.picasso.Picasso

class WishlistRecyclerAdapter(private val onRemove: (product: Product) -> Unit,
                               private val onOpenProduct: (product: Product) -> Unit):
    RecyclerView.Adapter<WishlistRecyclerAdapter.WishlistRecyclerHolder>() {
    private var wishlist: MutableList<Product> = mutableListOf()
    fun setWishlist(wishlist: MutableList<Product>) {
        this.wishlist = wishlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistRecyclerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerWishlistBinding.inflate(inflate, parent, false)
        return WishlistRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistRecyclerHolder, position: Int) {
        Picasso.get().load(wishlist[position].image1).into(holder.binding.productImg)
        holder.binding.productLayout.setOnClickListener { onOpenProduct(wishlist[position]) }
        holder.binding.closeBtn.setOnClickListener { onRemove(wishlist[position]) }
        holder.binding.productName.text = wishlist[position].name
        holder.binding.productPrice.text = "$" + wishlist[position].price
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }

    inner class WishlistRecyclerHolder(
        val binding: RecyclerWishlistBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}