package com.godwpfh.myapplication.ui.home.profile

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.data.remote.GithubClient
import com.godwpfh.myapplication.data.remote.response.ResponseGetUser
import com.godwpfh.myapplication.data.remote.response.ResponseRepos
import com.godwpfh.myapplication.databinding.FragmentProfileBinding
import com.godwpfh.myapplication.ui.home.HomeActivity
import com.godwpfh.myapplication.ui.setting.SettingActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val followFragment = FollowFragment()
    private val reposFragment = ReposFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentProfileBinding.inflate(layoutInflater, container, false)

        initProfile()
        initTransaction()
        initSetting()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initTransaction() {
        //처음에 보여질 때 follow로 뜨게
        childFragmentManager.beginTransaction()
            .add(R.id.profile_fragmentview, followFragment)
            .commit()




        binding.homeFollowBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, followFragment)
                .commit()

            binding.homeFollowBtn.isSelected=true
            binding.homeReposBtn.isSelected=false
        }

        binding.homeReposBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, reposFragment)
                .commit()
            binding.homeReposBtn.isSelected=true
            binding.homeFollowBtn.isSelected=false
        }
    }

    private fun initProfile(){
        val call : Call<ResponseGetUser> = GithubClient.githubService.getUserInfo("wlwpfh")
        call.enqueue(object : Callback<ResponseGetUser> {
            override fun onResponse(
                call: Call<ResponseGetUser>,
                response: Response<ResponseGetUser>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    binding.textviewProfileUserId.text=data?.login
                    binding.textviewProfileUserName.text=data?.name
                    Glide.with(this@ProfileFragment)
                        .load(data?.avatar_url)
                        .into(binding.imageviewProfile)
            }else{
                    Log.d(TAG,"ProfileFragment - onResponse(), reponse not successful")
                }
            }

            override fun onFailure(call: Call<ResponseGetUser>, t: Throwable) {
                Log.d("NetworkTest", "ProfileFragment - onFailure() called, error:$t ")
            }
        })
        }

    private fun initSetting(){
        binding.imageviewSetting.setOnClickListener{
            val intent=Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }
    }
    }

