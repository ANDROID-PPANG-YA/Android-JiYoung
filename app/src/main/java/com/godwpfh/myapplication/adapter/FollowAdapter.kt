package com.godwpfh.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.godwpfh.myapplication.data.FollowData
import com.godwpfh.myapplication.databinding.ItemFollowListBinding

class FollowAdapter : RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {
    val followUserList = mutableListOf<FollowData>()

    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding =
            ItemFollowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.onBind(followUserList[position])
        holder.itemView.setOnClickListener {
            listener?.onFollowItemClick(it, followUserList[position], position)
        }
    }

    override fun getItemCount(): Int = followUserList.size

    inner class FollowViewHolder(
        private val binding: ItemFollowListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowData) {
            Glide.with(binding.root)
                .load(data.follow_image)
                .into(binding.imageviewFollowUser)
            binding.textviewFollowName.text = data.follow_name

            val pos=adapterPosition
            if(pos!=RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    listener?.onFollowItemClick(itemView, data, pos)
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onFollowItemClick(v: View, data: FollowData, pos: Int)
    }


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}