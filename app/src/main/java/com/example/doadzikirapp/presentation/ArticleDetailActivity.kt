package com.example.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.doadzikirapp.R
import com.example.doadzikirapp.databinding.ActivityArticleDetailBinding

class ArticleDetailActivity : AppCompatActivity() {
    private var _binding: ActivityArticleDetailBinding? = null
    private val binding get() = _binding as ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        _binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titleArticle = intent.getStringExtra("title")
        val contentArticle = intent.getStringExtra("content")
        val imageArticle = intent.getIntExtra("image", 1)

        binding.apply {
            tvDetailTitle.text = titleArticle
            tvDetailContent.text = contentArticle
            imgDetailArticle.setImageResource(imageArticle)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}