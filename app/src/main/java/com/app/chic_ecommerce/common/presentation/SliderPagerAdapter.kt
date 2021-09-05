package com.app.chic_ecommerce.common.presentation

import android.content.Context
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.common.data.entities.SliderItemModel
import com.app.chic_ecommerce.databinding.SliderItemBinding


class SliderPagerAdapter(var context: Context?, var items: List<SliderItemModel>)
    : PagerAdapter() {

    fun SliderPagerAdapter(context: Context, items: List<SliderItemModel>) {
        this.context = context
        this.items = items
    }

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): View {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding:SliderItemBinding = DataBindingUtil.inflate(inflater, R.layout.slider_item, container, false)
        binding.itemImage.setImageResource(items[position].image)
        binding.itemTitle.text = items[position].caption
        binding.itemBtn.setOnClickListener { items[position].onClick() }
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

    }

    override fun getCount(): Int {
        return items.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }
}