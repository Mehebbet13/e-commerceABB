package com.example.e_commerceabb.presentation.admin.products.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.AddedProductBinding
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.utils.load

class AddedProductsAdapter : RecyclerView.Adapter<AddedProductsAdapter.CartProductsViewHolder>() {
    private val orders: MutableList<Order> = mutableListOf()

    fun setData(data: List<Order>) {
        this.orders.clear()
        this.orders.addAll(data)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsViewHolder {
        val binding =
            AddedProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductsViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    inner class CartProductsViewHolder(private val binding: AddedProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.productName.text = order.name
            binding.productPrice.text = order.price
            binding.productImage.load(order.imageUrl)
        }
    }
}
