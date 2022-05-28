package com.example.androidappmvvm.ui.adapter

import com.example.androidappmvvm.model.entity.User

interface OnListItemClick {
    fun onItemClick(user: User)
}