package com.example.doadzikirapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doadzikirapp.databinding.ItemArticleBinding
import com.example.doadzikirapp.model.ArticleItem
import com.example.doadzikirapp.presentation.ArticleDetailActivity
import com.example.doadzikirapp.utils.OnItemCallback

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    // variable to store dataset
    private var listArticle = ArrayList<ArticleItem>()
    // variable to give event click of item in Activity through setOnItmCallback
    private var onItemCallback: OnItemCallback? = null

    fun setData (list: List<ArticleItem>){
        listArticle.clear()
        listArticle.addAll(list)
    }

    fun setOnItemClickCallback(onItemCallback: OnItemCallback) {
        this.onItemCallback = onItemCallback
    }

    inner class ArticleViewHolder(val view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount() = listArticle.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val data = listArticle[position]
        holder.view.itemArticle.setImageResource(data.imageArticle)
        // this is give click event for each item in viewpager
        holder.itemView.setOnClickListener {
            // set event click in activity through interface
            onItemCallback?.onItemClicked(data)

            /*// provide context for Intent
            it.context.apply {
                // navigate to DetailActivity
                val intent = Intent(this, ArticleDetailActivity::class.java)
                //navigate to DetailActivity with datas using putExtra
                intent.putExtra("title", data.titleArticle)
                intent.putExtra("content", data.contentArticle)
                intent.putExtra("image", data.imageArticle)
                startActivity(intent)
            }*/
        }
    }
}
