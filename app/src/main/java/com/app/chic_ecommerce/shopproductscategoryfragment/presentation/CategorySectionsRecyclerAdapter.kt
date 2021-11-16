package com.project.ecommerce.shopfragmentlayer3.presentation.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.databinding.RecyclerCatgorySectionsBinding
import com.app.chic_ecommerce.shopproductscategoryfragment.data.entities.SubCategory

class CategorySectionsRecyclerAdapter (val resources: Resources, val onItemAdded:(SubCategory)->Unit, val onItemRemoved:(SubCategory)->Unit)
    : RecyclerView.Adapter<CategorySectionsRecyclerAdapter.CategorySectionsRecyclerViewHolder>() {
    private var subCategories: MutableList<SubCategory> = mutableListOf()
    private var selectedSubCategories: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CategorySectionsRecyclerViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerCatgorySectionsBinding.inflate(inflate, parent, false)
        return CategorySectionsRecyclerViewHolder(binding)
    }

    fun setSections(subCategories: MutableList<SubCategory>) {
        this.subCategories = subCategories
        selectedSubCategories = mutableListOf()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategorySectionsRecyclerViewHolder, position: Int) {
        holder.binding.sectionText.text = subCategories[position].name
        holder.binding.root.setOnClickListener {
            val subCategory: String? = selectedSubCategories.firstOrNull { it == subCategories[position].name }
            if (subCategory == null){
                //add section
                holder.binding.sectionText.background = resources.getDrawable(R.drawable.category_section_selected_bg)
                selectedSubCategories.add(subCategories[position].name)
                onItemAdded(subCategories[position])
                notifyItemMoved(holder.bindingAdapterPosition, 0)
            }else{
                //remove section
                holder.binding.sectionText.background = resources.getDrawable(R.drawable.category_section_bg)
                selectedSubCategories.remove(subCategories[position].name)
                onItemRemoved(subCategories[position])
                notifyItemMoved(holder.bindingAdapterPosition, selectedSubCategories.size)
            }
        }
    }

    override fun getItemCount(): Int {
        return subCategories.size
    }

    inner class CategorySectionsRecyclerViewHolder(
            val binding: RecyclerCatgorySectionsBinding
    ) : RecyclerView.ViewHolder(binding.root) {}
}