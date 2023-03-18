package com.dicoding.aplikasiandroidsederhana.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.aplikasiandroidsederhana.model.News
import com.dicoding.aplikasiandroidsederhana.databinding.ItemCardNewsBinding

class NewsAdapter(private val listNews: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCardNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, _, image, author, _, date, category) = listNews[position]

        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.binding.authorImage)

        holder.binding.titleNews.text = title
        holder.binding.authorDateNews.text = String.format("%s - %s", author, date)
        holder.binding.category.text = category
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listNews[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listNews.size

    class ListViewHolder(var binding: ItemCardNewsBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }
}