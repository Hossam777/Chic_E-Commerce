package com.app.chic_ecommerce.cartfragment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chic_ecommerce.common.data.entities.CartProduct
import com.app.chic_ecommerce.databinding.RecyclerCartBinding
import com.squareup.picasso.Picasso

class CartRecyclerAdapter(private val onRemove: (product: CartProduct) -> Unit,
                          private val onAddOneQuantity: (product: CartProduct) -> Unit,
                          private val onRemoveOneQuantity: (product: CartProduct) -> Unit):
    RecyclerView.Adapter<CartRecyclerAdapter.CartRecyclerHolder>() {

    private var cartList: MutableList<CartProduct> = mutableListOf()

    fun setCartList(cartList: MutableList<CartProduct>) {
        this.cartList = cartList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartRecyclerHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecyclerCartBinding.inflate(inflate, parent, false)
        return CartRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: CartRecyclerHolder, position: Int) {
        Picasso.get().load(cartList[position].image).into(holder.binding.productImg)
        holder.binding.addOneProductBtn.setOnClickListener {
            onAddOneQuantity(cartList[position])
            holder.binding.productQuantity.text = (cartList[position].quantity + 1).toString()
        }
        holder.binding.removeOneProductBtn.setOnClickListener {
            if(holder.binding.productQuantity.text != "1"){
                onRemoveOneQuantity(cartList[position])
                holder.binding.productQuantity.text = (cartList[position].quantity - 1).toString()
            }
        }
        holder.binding.removeProductBtn.setOnClickListener { onRemove(cartList[position]) }
        holder.binding.productName.text = cartList[position].name
        holder.binding.productSize.text = cartList[position].size
        holder.binding.productColor.text = cartList[position].color
        holder.binding.productQuantity.text = cartList[position].quantity.toString()
        holder.binding.productPrice.text = "$" + cartList[position].price
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    inner class CartRecyclerHolder(
        val binding: RecyclerCartBinding
    ): RecyclerView.ViewHolder(binding.root) {}
}