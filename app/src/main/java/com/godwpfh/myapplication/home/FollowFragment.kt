package com.godwpfh.myapplication.home

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private var  _binding : FragmentFollowBinding ?= null
    private val binding get() = _binding!!

    private lateinit var  followAdatper: FollowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentFollowBinding.inflate(layoutInflater, container, false)
        Log.d(TAG,"FollowFragment - onCreateView() called")

        initFollowAdapter()

        return binding.root
    }

     private fun initFollowAdapter(){

        followAdatper = FollowAdapter()

        binding.fragmentFollowRecylcerview.adapter=followAdatper

        followAdatper.followUserList.addAll(
            listOf(
                FollowData(R.drawable.hyebin_image,"이혜빈", "T가 되고 싶은 F"),
                FollowData(R.drawable.jimin_image,"유지민", "안드는 하나야 둘이 될 수 없어"),
                FollowData(R.drawable.jiyeon_image,"정지연", "새벽 코딩 with 새벽 감성"),
                FollowData(R.drawable.jehun_image,"조재훈", "Android is my everything..."),
                FollowData(R.drawable.android_image,"JungEun Park", "JungEun Park"),
                FollowData(R.drawable.android_image,"백지연", "100Gyeon"),
                FollowData(R.drawable.android_image,"최시언", "noino0819")

                )
        )
        followAdatper.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
        Log.d(TAG,"FollowFragment - onDestroyView() called")
    }
}