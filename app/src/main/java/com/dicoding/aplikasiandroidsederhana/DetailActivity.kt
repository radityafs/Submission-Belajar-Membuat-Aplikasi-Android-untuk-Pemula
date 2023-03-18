package com.dicoding.aplikasiandroidsederhana

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.aplikasiandroidsederhana.adapter.CommentNewsAdapter
import com.dicoding.aplikasiandroidsederhana.model.News
import com.dicoding.aplikasiandroidsederhana.databinding.ActivityDetailNewsBinding
import com.dicoding.aplikasiandroidsederhana.model.CommentNews


@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }

    private lateinit var rvComment : RecyclerView
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val news = intent.getParcelableExtra<News>(EXTRA_NEWS)

        if (news != null) {
            binding.title.text = news.title
            binding.description.text = news.description
            binding.author.text = news.author
            binding.createdAt.text = news.date
            binding.category.text = news.category
            Glide.with(this)
                .load(news.image)
                .into(binding.backgroundImage)
            Glide.with(this)
                .load(news.author_image)
                .into(binding.authorImage)

            binding.shareButton.setOnClickListener{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, news.title)
                intent.putExtra(Intent.EXTRA_TEXT, """
                    Judul : ${news.title}
                    Kategori : ${news.category}
                    ${news.description}
                    
                    Ditulis Oleh : ${news.author}
                    Dibuat pada : ${news.date}
                """.trimIndent()
                )
                startActivity(Intent.createChooser(intent, "Share to: "))
            }
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

        rvComment = binding.recyclerViewComment
        generateRandomComment()
    }

    private fun generateRandomComment(){
        val dataComment = ArrayList<CommentNews>()

        val random = (1..20).random()
        val randomDate = "2021-0${(1..9).random()}-${(1..28).random()} - ${(1..23).random()}:${(1..59).random()}"

        for (i in 1..random) {
            val comment = CommentNews(
                "Comment $i",
                "Comment $i",
                "https://randomuser.me/api/portraits/thumb/men/$i.jpg",
                randomDate
            )
            dataComment.add(comment)
        }

        showRecyclerList(dataComment)
    }

    private fun showRecyclerList(listNews: ArrayList<CommentNews>){
        rvComment.layoutManager = LinearLayoutManager(this)

        val commentNewsAdapter = CommentNewsAdapter(listNews)
        rvComment.adapter = commentNewsAdapter
    }

}
