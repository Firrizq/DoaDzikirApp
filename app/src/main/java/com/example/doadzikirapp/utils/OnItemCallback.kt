package com.example.doadzikirapp.utils

import com.example.doadzikirapp.model.ArticleItem

interface OnItemCallback {
    fun onItemClicked(item: ArticleItem)
}