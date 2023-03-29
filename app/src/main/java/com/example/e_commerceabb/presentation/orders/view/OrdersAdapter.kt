package com.example.e_commerceabb.presentation.orders.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.OrderedProductBinding
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.utils.load

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    private val orders: MutableList<Order> = mutableListOf()
    var onButtonClick: ((order: Order) -> Unit)? = null

    fun setData(data: List<Order>) {
        this.orders.clear()
        this.orders.addAll(data)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding =
            OrderedProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    inner class OrdersViewHolder(private val binding: OrderedProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.productName.text = order.name
            binding.productPrice.text = order.price
            binding.status.text = order.status
            binding.productBtn.text = order.buttonName
            binding.productImage.load(order.imageUrl)
            binding.productBtn.setOnClickListener {
                onButtonClick?.invoke(order)
            }
            if (order.status == itemView.context.getString(R.string.completed)) {
                binding.status.setTextColor(itemView.context.getColor(R.color.done))
                binding.status.backgroundTintList = itemView.context.getColorStateList(R.color.done)
            } else {
                binding.status.setTextColor(itemView.context.getColor(R.color.main))
                binding.status.backgroundTintList = itemView.context.getColorStateList(R.color.main)
            }
        }
    }
}
