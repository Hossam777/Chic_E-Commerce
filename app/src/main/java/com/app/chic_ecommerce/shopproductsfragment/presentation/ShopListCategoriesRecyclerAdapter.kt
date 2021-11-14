package com.app.chic_ecommerce.shopproductsfragment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.databinding.RecyclerShoplistCategoriesBinding
import com.app.chic_ecommerce.shopproductsfragment.data.entities.Category

class ShopListCategoriesRecyclerAdapter(private val onCategoryClicked: (category: String) -> Unit):
    RecyclerView.Adapter<ShopListCategoriesRecyclerAdapter.ShopListCategoriesRecyclerHolder>() {
    private var categories: MutableList<Category> = mutableListOf()

    fun setCategories(categories: MutableList<Category>) {
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
            onCategoryClicked(categories[position].name)
            //holder.binding.shopProductsCountTxt.setTextColor(resources.getColor(R.color.pallet_red))
        }
        holder.binding.shopProductsCountTxt.text = categories[position].name
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ShopListCategoriesRecyclerHolder(
        val binding: RecyclerShoplistCategoriesBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}