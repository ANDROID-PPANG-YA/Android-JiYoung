package com.godwpfh.myapplication.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.godwpfh.myapplication.databinding.FragmentReposBinding
import com.godwpfh.myapplication.home.adapter.ReposAdapter
import com.godwpfh.myapplication.home.data.ReposData

class ReposFragment : Fragment() {
    private var  _binding : FragmentReposBinding?= null
    private val binding get() = _binding!!

    private lateinit var reposAdapter: ReposAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentReposBinding.inflate(layoutInflater, container, false)

        Log.d(TAG,"ReposFragment - onCreateView() called")

        initReposAdapter()

        return binding.root

    }

    private fun initReposAdapter(){
        //recyclerview에 adapter연결
        reposAdapter = ReposAdapter()

        binding.fragmentReposRecyclerview.adapter= reposAdapter

        reposAdapter.reposList.addAll(
            listOf(
               ReposData("algorithm","studying algorithm"),
               ReposData("data-science-study","study data science"),
               ReposData("studyTight","online study planner for students!"),
               ReposData("instagram-clone","instagram clone coding with Java, Kotlin, Firebase"),
                ReposData("pla_bear","플라스틱 쓰레기를 줄이는 환경보호 앱, PlaBear"),
                ReposData("openCV_project","동전 분류 인식 프로그램")

            )
        )
        reposAdapter.notifyDataSetChanged()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
        Log.d(TAG,"ReposFragment - onDestroyView() called")
    }
}