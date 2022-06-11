package com.godwpfh.myapplication.ui.home.profile

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.FragmentFollowBinding
import com.godwpfh.myapplication.adapter.FollowAdapter
import com.godwpfh.myapplication.data.FollowData
import com.godwpfh.myapplication.data.ReposData
import com.godwpfh.myapplication.data.remote.GithubClient
import com.godwpfh.myapplication.data.remote.response.ResponseFollow
import com.godwpfh.myapplication.data.remote.response.ResponseGetUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowFragment : Fragment() {
    private var  _binding : FragmentFollowBinding ?= null
    private val binding get() = _binding!!

    private lateinit var  followAdatper: FollowAdapter
    private val followData = mutableListOf<FollowData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentFollowBinding.inflate(layoutInflater, container, false)
        Log.d(TAG,"FollowFragment - onCreateView() called")

        initFollowings()

        return binding.root
    }

    private fun initFollowings(){
        val call : Call<List<ResponseFollow>> = GithubClient.githubService.getFollowings("wlwpfh")
        call.enqueue(object : Callback<List<ResponseFollow>> {
            override fun onResponse(
                call: Call<List<ResponseFollow>>,
                response: Response<List<ResponseFollow>>
            ) {
                if(response.isSuccessful){
                    val followList=response.body()
                    Log.d(TAG,"FollowFragment - onResponse() called followList=$followList")
//                    followList!!.forEachIndexed { index, _ ->
//                        followData.add(FollowData(followList[index].image, followList[index].name))
//                    }
                    initFollowAdapter()
                }else{

                }
            }

            override fun onFailure(call: Call<List<ResponseFollow>>, t: Throwable) {
                Log.d(TAG,"FollowFragment - onFailure() called t:$t")
            }


        })
    }

     private fun initFollowAdapter(){

        followAdatper = FollowAdapter()

        binding.fragmentFollowRecylcerview.adapter=followAdatper

        followAdatper.followUserList.addAll(followData)
        followAdatper.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
        Log.d(TAG,"FollowFragment - onDestroyView() called")
    }
}