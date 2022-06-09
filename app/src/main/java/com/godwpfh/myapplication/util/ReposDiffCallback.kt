package com.godwpfh.myapplication.util

import androidx.recyclerview.widget.DiffUtil
import com.godwpfh.myapplication.data.ReposData

object ReposDiffCallback : DiffUtil.ItemCallback<ReposData>() {
    override fun areItemsTheSame(oldItem: ReposData, newItem: ReposData): Boolean
    = oldItem.repos_des==newItem.repos_des && oldItem.repos_name==newItem.repos_name

    override fun areContentsTheSame(oldItem: ReposData, newItem: ReposData): Boolean
    = oldItem==newItem
}