package com.project.ecommerce.shopfragmentlayer3.presentation.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.R
import com.app.chic_ecommerce.databinding.RecyclerCatgorySectionsBinding

class CategorySectionsRecyclerAdapter (val resources: Resources, val onItemAdded:(String)->Unit, val onItemRemoved:(String)->Unit)
    : RecyclerView.Adapter<CategorySectionsRecyclerAdapter.CategorySectionsRecyclerViewHolder>() {
    private var sections: MutableList<String> = mutableListOf()
    private var selectedSections: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CategorySectionsRecyclerViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerCatgorySectionsBinding.inflate(inflate, parent, false)
        return CategorySectionsRecyclerViewHolder(binding)
    }

    fun setSections(sections: MutableList<String>) {
        this.sections = sections
        selectedSections = mutableListOf()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategorySectionsRecyclerViewHolder, position: Int) {
        holder.binding.sectionText.text = sections[position]
        holder.binding.root.setOnClickListener {
            val section: String? = selectedSections.firstOrNull { it == sections[position] }
            if (section == null){
                //add section
                holder.binding.sectionText.background = resources.getDrawable(R.drawable.category_section_selected_bg)
                selectedSections.add(sections[position])
                onItemAdded(sections[position])
                notifyItemMoved(holder.bindingAdapterPosition, 0)
            }else{
                //remove section
                holder.binding.sectionText.background = resources.getDrawable(R.drawable.category_section_bg)
                selectedSections.remove(sections[position])
                onItemRemoved(sections[position])
                notifyItemMoved(holder.bindingAdapterPosition, selectedSections.size)
            }
        }
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    inner class CategorySectionsRecyclerViewHolder(
            val binding: RecyclerCatgorySectionsBinding
    ) : RecyclerView.ViewHolder(binding.root) {}
}