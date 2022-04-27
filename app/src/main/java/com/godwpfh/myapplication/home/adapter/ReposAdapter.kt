package com.godwpfh.myapplication.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godwpfh.myapplication.databinding.ItemReposListBinding
import com.godwpfh.myapplication.home.data.ReposData

class ReposAdapter: RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {
    val reposList = mutableListOf<ReposData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding= ItemReposListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.onBind(reposList[position])
    }

    override fun getItemCount(): Int = reposList.size

    class ReposViewHolder(
        private val binding : ItemReposListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : ReposData){
            binding.textviewReposDes.text=data.repos_des
            binding.textviewReposName.text=data.repos_name
        }

    }
}