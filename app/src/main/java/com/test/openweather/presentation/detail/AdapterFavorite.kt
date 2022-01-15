package com.test.openweather.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.openweather.databinding.ItemFavoriteBinding
import com.test.openweather.model.Favorite

class AdapterFavorite(
    private val items: List<Favorite>,
    private val onItemClick : (positiom: Int) -> Unit
): RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {


    inner class ViewHolder(mainbinding: ItemFavoriteBinding):
    RecyclerView.ViewHolder(mainbinding.root){
        val binding = mainbinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNama.text = item.city_name
        holder.binding.layoutItem.setOnClickListener{
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}