package com.dicoding.aplikasiandroidsederhana

import com.dicoding.aplikasiandroidsederhana.adapter.NewsAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.aplikasiandroidsederhana.model.News
import com.dicoding.aplikasiandroidsederhana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvNews : RecyclerView
    private var listNews : ArrayList<News> = arrayListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        rvNews = binding.recyclerView
        listNews.addAll(getDataNews())

        showRecyclerList()
    }

    private fun showRecyclerList(){
        rvNews.layoutManager = LinearLayoutManager(this)
        val NewsAdapter = NewsAdapter(listNews)
        rvNews.adapter = NewsAdapter

        NewsAdapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_NEWS, data)
                startActivity(intent)
            }
        })
    }

    private fun getDataNews() : ArrayList<News>{
        val dataNews = ArrayList<News>();

        val dataTitle = resources.getStringArray(R.array.data_title_news)
        val dataDescription = resources.getStringArray(R.array.data_description_news)
        val dataImage = resources.getStringArray(R.array.data_image_news)
        val dataAuthor = resources.getStringArray(R.array.data_author_news)
        val dataAuthorImage = resources.getStringArray(R.array.data_author_image_news)
        val dataDate = resources.getStringArray(R.array.data_created_at_news)
        val dataCategory = resources.getStringArray(R.array.data_category_news)

        for (i in dataTitle.indices){
            val news = News(
                dataTitle[i],
                dataDescription[i],
                dataImage[i],
                dataAuthor[i],
                dataAuthorImage[i],
                dataDate[i],
                dataCategory[i]
            )
            dataNews.add(news)
        }

        return dataNews
    }
}