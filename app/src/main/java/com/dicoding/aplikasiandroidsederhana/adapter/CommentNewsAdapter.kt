package com.dicoding.aplikasiandroidsederhana.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.aplikasiandroidsederhana.databinding.ItemCardCommentNewsBinding
import com.dicoding.aplikasiandroidsederhana.model.CommentNews

class CommentNewsAdapter(private val listNews: ArrayList<CommentNews>) : RecyclerView.Adapter<CommentNewsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCardCommentNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, comment, image, date) = listNews[position]

        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.binding.imageAuthorComment)

        holder.binding.nameCommentNews.text = name
        holder.binding.descriptionCommentNews.text = comment
        holder.binding.dateCommentNews.text = date
    }

    override fun getItemCount(): Int = listNews.size

    class ListViewHolder(var binding: ItemCardCommentNewsBinding) : RecyclerView.ViewHolder(binding.root)

}