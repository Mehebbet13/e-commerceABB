package com.example.e_commerceabb.presentation.favorites.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.FavoriteProductBinding
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.utils.load

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private val orders: MutableList<Order> = mutableListOf()
    var onFavButtonClick: ((productId: String) -> Unit)? = null
    var addToCardClick: ((productId: String) -> Unit)? = null

    fun setData(data: List<Order>) {
        this.orders.clear()
        this.orders.addAll(data)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding =
            FavoriteProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    inner class FavoritesViewHolder(private val binding: FavoriteProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.productName.text = order.name
            binding.productPrice.text = order.price
            binding.productImage.load(order.imageUrl)
            binding.favButton.setOnClickListener {
                onFavButtonClick?.invoke(order.id)
            }
            binding.btnAddToCart.setOnClickListener {
                addToCardClick?.invoke(order.id)
            }
        }
    }
}
