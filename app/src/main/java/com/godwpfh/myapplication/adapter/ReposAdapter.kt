package com.godwpfh.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.godwpfh.myapplication.data.ReposData
import com.godwpfh.myapplication.databinding.ItemReposListBinding
import com.godwpfh.myapplication.util.ReposDiffCallback

class ReposAdapter : ListAdapter<ReposData, ReposAdapter.ReposViewHolder>(ReposDiffCallback) {
    val reposList = mutableListOf<ReposData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding =
            ItemReposListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.onBind(reposList[position])
    }

    override fun getItemCount(): Int = reposList.size

    class ReposViewHolder(
        private val binding: ItemReposListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ReposData) {
            binding.textviewReposDes.text = data.repos_des
            binding.textviewReposName.text = data.repos_name
        }

    }

    interface OnItemClickListener {
        fun onReposItemClick(v: View, data: ReposData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}