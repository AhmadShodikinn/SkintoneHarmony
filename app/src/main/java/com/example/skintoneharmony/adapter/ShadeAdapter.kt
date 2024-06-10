package com.example.skintoneharmony

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.databinding.ShadeListBinding

class ShadeAdapter : ListAdapter<SkintoneResponseItem, ShadeAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ShadeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(val binding: ShadeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shade: SkintoneResponseItem) {
            Glide.with(binding.imageView.context)
                .load(shade.imageUrl)
                .placeholder(R.drawable.splash_background)
                .into(binding.imageView)
            binding.textViewDescription.text = shade.description
//            binding.textViewSkintone.text = shade.skintone
            binding.textViewBrands.text = shade.recommendedBrands.joinToString(", ")
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SkintoneResponseItem>() {
            override fun areItemsTheSame(oldItem: SkintoneResponseItem, newItem: SkintoneResponseItem): Boolean {
                return oldItem.shadeId == newItem.shadeId
            }

            override fun areContentsTheSame(oldItem: SkintoneResponseItem, newItem: SkintoneResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
