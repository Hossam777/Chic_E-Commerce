package com.app.chic_ecommerce.shopproductsfragment.presentation

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.databinding.RecyclerShoplistCategoriesBinding
import com.app.chic_ecommerce.databinding.RecyclerWishlistBinding
import com.squareup.picasso.Picasso

class ShopListCategoriesRecyclerAdapter(private val resources: Resources, private val onCategoryClicked: (category: String) -> Unit):
    RecyclerView.Adapter<ShopListCategoriesRecyclerAdapter.ShopListCategoriesRecyclerHolder>() {
    private var categories: MutableList<String> = mutableListOf()

    fun setCategories(categories: MutableList<String>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListCategoriesRecyclerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerShoplistCategoriesBinding.inflate(inflate, parent, false)
        return ShopListCategoriesRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopListCategoriesRecyclerHolder, position: Int) {
        holder.binding.shopListCategoryItemLayout.setOnClickListener {
            onCategoryClicked(categories[position])
            //holder.binding.shopProductsCountTxt.setTextColor(resources.getColor(R.color.pallet_red))
        }
        holder.binding.shopProductsCountTxt.text = categories[position]
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ShopListCategoriesRecyclerHolder(
        val binding: RecyclerShoplistCategoriesBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}