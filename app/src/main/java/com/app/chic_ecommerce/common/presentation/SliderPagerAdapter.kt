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
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.net.URL


class SliderPagerAdapter(var context: Context?, var items: List<SliderItemModel>, var hideBtn: Boolean)
    : PagerAdapter() {

    private fun isValid(url: String): Boolean {
        return try {
            URL(url).toURI()
            true
        }catch (e: Exception) {
            false
        }
    }

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): View {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding:SliderItemBinding = DataBindingUtil.inflate(inflater, R.layout.slider_item, container, false)
        if(isValid(items[position].imageURL)){
            Picasso.get().load(items[position].imageURL).into(binding.itemImage)
        }else{
            binding.itemImage.setImageResource(items[position].image)
        }
        binding.itemTitle.text = items[position].caption
        binding.itemBtn.setOnClickListener { items[position].onClick() }
        if (hideBtn){
            binding.itemBtn.visibility = View.INVISIBLE
        }
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