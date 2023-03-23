package com.example.e_commerceabb.utils

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected val list: MutableList<T> = arrayListOf()

    override fun getItemCount(): Int = list.size

    fun setData(items: List<T>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}