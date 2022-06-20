package com.godwpfh.myapplication.ui.home.profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.godwpfh.myapplication.adapter.FollowAdapter
import com.godwpfh.myapplication.data.FollowData
import com.godwpfh.myapplication.data.remote.GithubClient
import com.godwpfh.myapplication.data.remote.response.ResponseFollow
import com.godwpfh.myapplication.databinding.FragmentFollowBinding
import com.godwpfh.myapplication.util.CustomRecyclerDecoration
import retrofit2.Call
import com.godwpfh.myapplication.util.showToast
import retrofit2.Callback
import retrofit2.Response

class FollowFragment : Fragment() {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var followAdatper: FollowAdapter
    private val followData = mutableListOf<FollowData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFollowBinding.inflate(layoutInflater, container, false)
        Log.d(TAG, "FollowFragment - onCreateView() called")

        initFollowings()
        initRecylcerDeco()
        return binding.root
    }

    private fun initFollowings() {
        val call: Call<List<ResponseFollow>> = GithubClient.githubService.getFollowings("wlwpfh")
        call.enqueue(object : Callback<List<ResponseFollow>> {
            override fun onResponse(
                call: Call<List<ResponseFollow>>,
                response: Response<List<ResponseFollow>>
            ) {
                if (response.isSuccessful) {
                    val followList = response.body()
                    followList!!.forEachIndexed { index, _ ->
                        followData.add(
                            FollowData(
                                follow_image = followList[index].image,
                                follow_name = followList[index].name
                            )
                        )
                    }
                    initFollowAdapter()
                } else {
                    Log.d(TAG, "FollowFragment - onResponse() called, Response is not successful")
                }
            }

            override fun onFailure(call: Call<List<ResponseFollow>>, t: Throwable) {
                Log.d(TAG, "FollowFragment - onFailure() called t:$t")
            }


        })
    }

    private fun initFollowAdapter() {

        followAdatper = FollowAdapter()

        binding.fragmentFollowRecylcerview.adapter = followAdatper

        followAdatper.followUserList.addAll(followData)
        followAdatper.notifyDataSetChanged()
        initFollowClick()
    }

    private fun initRecylcerDeco() {
        val customRecyclerDecoration = CustomRecyclerDecoration(10f, 10f, Color.WHITE)
        binding.fragmentFollowRecylcerview.addItemDecoration(customRecyclerDecoration)
    }

    private fun initFollowClick(){
        followAdatper.setOnItemClickListener(object: FollowAdapter.OnItemClickListener{
            override fun onFollowItemClick(v: View, data: FollowData, pos: Int) {
                Intent(context, DetailActivity::class.java).apply{

                    putExtra("follow_data", data)
                }.run {
                    startActivity(this)
                }

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "FollowFragment - onDestroyView() called")
    }
}