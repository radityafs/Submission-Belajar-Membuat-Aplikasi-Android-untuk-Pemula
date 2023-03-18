package com.dicoding.aplikasiandroidsederhana

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.aplikasiandroidsederhana.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val authorImage = resources.getString(R.string.app_image_author)

        Glide.with(this)
            .load(authorImage)
            .into(binding.authorImage)

        binding.github.setOnClickListener {
            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/radityafs/"))
            startActivity(githubIntent)
        }

        binding.gmail.setOnClickListener {
            val gmailIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("mailto:radit@student.uns.ac.id"))
            startActivity(gmailIntent)
        }


    }
}
