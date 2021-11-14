package com.app.chic_ecommerce.productactivity.presentation

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.entities.Color
import com.app.chic_ecommerce.common.data.entities.Product
import com.app.chic_ecommerce.common.data.mockup.categories
import com.app.chic_ecommerce.databinding.RecyclerColorsBinding
import com.app.chic_ecommerce.databinding.RecyclerShoplistCategoriesBinding
import com.app.chic_ecommerce.databinding.RecyclerWishlistBinding
import com.squareup.picasso.Picasso

class ColorsRecyclerAdapter(private val owner: LifecycleOwner, private val resources: Resources, private val onColorClicked: (color: Color) -> Unit):
    RecyclerView.Adapter<ColorsRecyclerAdapter.ColorsRecyclerHolder>() {
    private var colors: MutableList<Color> = mutableListOf()
    private var selectedColor: MutableLiveData<String> = MutableLiveData()

    fun setColors(colors: MutableList<Color>) {
        this.colors = colors
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsRecyclerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerColorsBinding.inflate(inflate, parent, false)
        return ColorsRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorsRecyclerHolder, position: Int) {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        holder.binding.colorBtn.backgroundTintList = ColorStateList.valueOf(android.graphics.Color.parseColor(colors[position].code))
        holder.binding.colorBtn.setOnClickListener {
            selectedColor.postValue(colors[position].name)
        }
        selectedColor.observe(owner, {
            if(it == colors[position].name){
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                holder.binding.colorBorder.backgroundTintList = resources.getColorStateList(R.color.pallet_black)
            }else{
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                holder.binding.colorBorder.backgroundTintList = resources.getColorStateList(R.color.transparent)
            }
            onColorClicked(colors[position])
        })
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    inner class ColorsRecyclerHolder(
        val binding: RecyclerColorsBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}