package com.example.e_commerceabb.presentation.cart.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.CartProductBinding
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.utils.load

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartProductsViewHolder>() {
    private val orders: MutableList<Order> = mutableListOf()
    var onPlusClick: ((productId: String, count: Int) -> Unit)? = null
    var onMinusClick: ((productId: String) -> Unit)? = null
    var onDeleteClick: ((productId: String) -> Unit)? = null

    fun setData(data: List<Order>) {
        this.orders.clear()
        this.orders.addAll(data)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsViewHolder {
        val binding =
            CartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductsViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    inner class CartProductsViewHolder(private val binding: CartProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            var count = order.count
            binding.productName.text = order.name
            binding.productPrice.text = order.price
            binding.count.text = order.count.toString()
            binding.productImage.load(order.imageUrl)
            binding.minus.setOnClickListener {
                onMinusClick?.invoke(order.id)
                notifyItemRangeRemoved(orders.indexOf(order), orders.size - 1)
            }
            binding.plus.setOnClickListener {
                count++
                onPlusClick?.invoke(order.id, count)
            }
            binding.deleteButton.setOnClickListener {
                onDeleteClick?.invoke(order.id)
                notifyItemRangeRemoved(orders.indexOf(order), orders.size - 1)
            }
        }
    }
}
