package com.godwpfh.myapplication.ui.home.profile

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.godwpfh.myapplication.adapter.ReposAdapter
import com.godwpfh.myapplication.data.ReposData
import com.godwpfh.myapplication.databinding.FragmentReposBinding

class ReposFragment : Fragment() {
    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    private lateinit var reposAdapter: ReposAdapter

    val reposData = mutableListOf<ReposData>().apply {
        add(ReposData("algorithm", "studying algorithm"))
        add(ReposData("data-science-study", "study data science"))
        add(ReposData("studyTight", "online study planner for students!"))
        add(ReposData("instagram-clone", "instagram clone coding with Java, Kotlin, Firebase"))
        add(ReposData("pla_bear", "플라스틱 쓰레기를 줄이는 환경보호 앱, PlaBear"))
        add(ReposData("openCV_project", "동전 분류 인식 프로그램"))
    }

    //        listOf(
//            ReposData("algorithm","studying algorithm"),
//            ReposData("data-science-study","study data science"),
//            ReposData("studyTight","online study planner for students!"),
//            ReposData("instagram-clone","instagram clone coding with Java, Kotlin, Firebase"),
//            ReposData("pla_bear","플라스틱 쓰레기를 줄이는 환경보호 앱, PlaBear"),
//            ReposData("openCV_project","동전 분류 인식 프로그램")
//
//        )
//    ) addAll은 boolean을 리턴해서 하나씩 add함 -
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReposBinding.inflate(layoutInflater, container, false)

        Log.d(TAG, "ReposFragment - onCreateView() called")



        initReposAdapter()

        return binding.root

    }

    private fun initReposAdapter() {
        //recyclerview에 adapter연결
        reposAdapter = ReposAdapter()

        binding.fragmentReposRecyclerview.adapter = reposAdapter

        reposAdapter.reposList.addAll(reposData)
        reposAdapter.submitList(reposData)
        //이게 맞나... 흠..
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "ReposFragment - onDestroyView() called")
    }
}