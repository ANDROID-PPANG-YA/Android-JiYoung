package com.godwpfh.myapplication.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godwpfh.myapplication.databinding.ItemFollowListBinding

class RecylcerViewAdapter : RecyclerView.Adapter<RecylcerViewAdapter.RecyclerViewHolder>() {
    val userList = mutableListOf<FollowData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder{
         val binding=ItemFollowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class RecyclerViewHolder(
        private val binding : ItemFollowListBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : FollowData){
            binding.textviewFollowName.text=data.follow_name
            binding.textviewFollowDes.text=data.follow_des
        }
    }

}