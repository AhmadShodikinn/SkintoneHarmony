package com.example.skintoneharmony

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skintoneharmony.data.response.SkintoneResponseItem
import com.example.skintoneharmony.databinding.ShadeListBinding
import com.example.skintoneharmony.tools.Utils.toParagraphCase

class ShadeAdapter : ListAdapter<SkintoneResponseItem, ShadeAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ShadeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tone = getItem(position)
        holder.bind(tone, position)


    }

    class MyViewHolder(val binding: ShadeListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shade: SkintoneResponseItem, position: Int) {
            Glide.with(binding.imageView.context)
                .load(shade.imageUrl)
                .placeholder(R.drawable.vector_image_primary)
                .into(binding.imageView)
            binding.textViewDescription.text = shade.description.toParagraphCase()
            binding.textViewBrands.text = shade.recommendedBrands.joinToString(", ").toParagraphCase()

            val context = binding.root.context
            val formattedText = context.getString(R.string.analysis_results, (position + 1).toString())
            binding.analysisResults.text = formattedText
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
