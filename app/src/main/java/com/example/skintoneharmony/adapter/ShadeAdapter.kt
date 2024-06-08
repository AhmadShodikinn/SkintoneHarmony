package com.example.skintoneharmony

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skintoneharmony.data.response.SkintoneResponseItem

class ShadeAdapter(private val shades: List<SkintoneResponseItem>) : RecyclerView.Adapter<ShadeAdapter.ShadeViewHolder>() {

    inner class ShadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewSkintone: TextView = itemView.findViewById(R.id.textViewSkintone)
        val textViewBrands: TextView = itemView.findViewById(R.id.textViewBrands)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShadeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shade_list, parent, false)
        return ShadeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShadeViewHolder, position: Int) {
        val shade = shades[position]
        Glide.with(holder.itemView.context).load(shade.imageUrl).into(holder.imageView)
        holder.textViewDescription.text = shade.description
        holder.textViewSkintone.text = shade.skintone
        holder.textViewBrands.text = shade.recommendedBrands.joinToString(", ")
    }

    override fun getItemCount(): Int {
        return shades.size
    }
}
