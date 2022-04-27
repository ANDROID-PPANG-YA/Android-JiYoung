package com.godwpfh.myapplication.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godwpfh.myapplication.databinding.ItemFollowListBinding

class FollowAdapter : RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {
    val followUserList = mutableListOf<FollowData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder{
         val binding=ItemFollowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.onBind(followUserList[position])
    }

    override fun getItemCount(): Int {
        return followUserList.size
    }

    class FollowViewHolder(
        private val binding : ItemFollowListBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : FollowData){
            binding.imageviewFollowUser.setImageResource(data.follow_image)
            binding.textviewFollowName.text=data.follow_name
            binding.textviewFollowDes.text=data.follow_des
        }

    }

}