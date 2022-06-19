package com.godwpfh.myapplication.ui.home.profile

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.godwpfh.myapplication.adapter.ReposAdapter
import com.godwpfh.myapplication.data.ReposData
import com.godwpfh.myapplication.data.remote.GithubClient
import com.godwpfh.myapplication.data.remote.response.ResponseRepos
import com.godwpfh.myapplication.databinding.FragmentReposBinding
import com.godwpfh.myapplication.util.CustomRecyclerDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposFragment : Fragment() {
    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    private lateinit var reposAdapter: ReposAdapter

    private val reposData = mutableListOf<ReposData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReposBinding.inflate(layoutInflater, container, false)

        Log.d(TAG, "ReposFragment - onCreateView() called")


        initRepos()
        initRecylcerDeco()

        return binding.root

    }

    private fun initRepos() {
        Log.d(TAG, "ReposFragment - initRepos() called")
        val call: Call<List<ResponseRepos>> = GithubClient.githubService.getRepos("wlwpfh")
        call.enqueue(object : Callback<List<ResponseRepos>> {
            override fun onResponse(
                call: Call<List<ResponseRepos>>,
                response: Response<List<ResponseRepos>>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()
                    Log.d(TAG, "ReposFragment - onResponse() called list : $list")
                    list!!.forEachIndexed { index, _ ->
                        reposData.add(ReposData(list[index].name, list[index].description))
                    }
                    initReposAdapter()
                }
            }

            override fun onFailure(call: Call<List<ResponseRepos>>, t: Throwable) {
                Log.d(TAG, "ReposFragment - onFailure() called t=$t")
            }

        })

    }

    private fun initReposAdapter() {
        //recyclerview에 adapter연결
        reposAdapter = ReposAdapter()

        binding.fragmentReposRecyclerview.adapter = reposAdapter

        reposAdapter.reposList.addAll(reposData)

        reposAdapter.submitList(reposData)
    }

    private fun initRecylcerDeco() {
        val customRecyclerDecoration = CustomRecyclerDecoration(10f, 10f, Color.WHITE)
        binding.fragmentReposRecyclerview.addItemDecoration(customRecyclerDecoration)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "ReposFragment - onDestroyView() called")
    }
}