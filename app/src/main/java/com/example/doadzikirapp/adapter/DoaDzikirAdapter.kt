package com.example.doadzikirapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doadzikirapp.R
import com.example.doadzikirapp.model.DoaDzikirItem

// Adapter is a subclass from RecyclerView which take responsibility
// for providing views that represent items (layout item) in a data set
class DoaDzikirAdapter : RecyclerView.Adapter<DoaDzikirAdapter.DzikirViewHolder>(){
    private val listData = ArrayList<DoaDzikirItem>()

    fun setData(list: List<DoaDzikirItem>) {
        listData.clear()
        listData.addAll(list)
    }

    // ViewHolder take responsibility for initialize item from layout
    // in this class, we will describe our view from layout
    inner class DzikirViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemTitle = view.findViewById<TextView>(R.id.item_title)
        val itemArabic = view.findViewById<TextView>(R.id.item_arabic)
        val itemTranslate = view.findViewById<TextView>(R.id.item_translate)
    }

    // onCreateViewHolder provides layout to be used by ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DzikirViewHolder {
        return DzikirViewHolder(
            // LayoutInflater is a class to inflate a layout/view
            LayoutInflater.from(parent.context).inflate(R.layout.item_doa, parent, false)
        )
    }

    override fun getItemCount() = listData.size

    // onBindViewHolder distributing data in their position on item view
    override fun onBindViewHolder(holder: DzikirViewHolder, position: Int) {
        val data = listData[position]
        holder.apply {
            itemTitle.text = data.title
            itemArabic.text = data.arabic
            itemTranslate.text = data.translate
        }
    }
}