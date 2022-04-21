package com.godwpfh.myapplication.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godwpfh.myapplication.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private lateinit var  binding : FragmentFollowBinding
    private lateinit var  recyclerViewAdapter: RecylcerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFollowBinding.inflate(layoutInflater)

        initAdapter()
        Log.d(TAG,"FollowFragment - onCreateView() called : initAdapter선언 후 ")
        return binding.root

    }

    private fun initAdapter(){
        recyclerViewAdapter = RecylcerViewAdapter()

        binding.fragmentFollowRecylcerview.adapter= recyclerViewAdapter

        recyclerViewAdapter.userList.addAll(
            listOf(
                FollowData("양지영", "안드로이드 YB"),
                FollowData("양지영", "안드로이드 YB"),
                FollowData("양지영", "안드로이드 YB안드로이드 YB안드로이드 YB안드로이드 YB"),
                FollowData("양지영", "안드로이드 YB"),
                FollowData("양지영", "안드로이드 YB"),
                FollowData("양지영", "안드로이드 YB")
                )
        )
        recyclerViewAdapter.notifyDataSetChanged()
    }
}