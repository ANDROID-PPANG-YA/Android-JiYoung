package com.godwpfh.myapplication.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.data.remote.GithubClient
import com.godwpfh.myapplication.data.remote.response.ResponseGetUser
import com.godwpfh.myapplication.data.remote.response.ResponseSignIn
import com.godwpfh.myapplication.databinding.FragmentProfileBinding
import com.godwpfh.myapplication.databinding.FragmentReposBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val followFragment = FollowFragment()
    private val reposFragment = ReposFragment()

    private lateinit var email : String
    override fun onAttach(context: Context) {
        super.onAttach(context)
        email= (activity as HomeActivity).getUserEmail()
        Log.d(TAG,"ProfileFragment - onAttach() called email: $email")
        initProfile(email)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentProfileBinding.inflate(layoutInflater, container, false)

        initTransaction()


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

        binding.homeFollowBtn.isSelected = true
        binding.homeReposBtn.isSelected = false


        binding.homeFollowBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, followFragment)
                .commit()

            binding.homeFollowBtn.isSelected=!binding.homeReposBtn.isSelected
        }

        binding.homeReposBtn.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.profile_fragmentview, reposFragment)
                .commit()
            binding.homeReposBtn.isSelected=!binding.homeFollowBtn.isSelected
        }
    }

    private fun initProfile(email_ : String){
        val call : Call<ResponseGetUser> = GithubClient.githubService.getUserInfo(email_)
        call.enqueue(object : Callback<ResponseGetUser> {
            override fun onResponse(
                call: Call<ResponseGetUser>,
                response: Response<ResponseGetUser>
            ) {
                if(response.isSuccessful){
                    val data = response.body()?.data
                    binding.textviewProfileUserId.text=data?.login
                    binding.textviewProfileUserName.text=data?.name
                    Glide.with(this@ProfileFragment)
                        .load(data?.avatar_url)
                        .into(binding.imageviewProfile)
            }
            }

            override fun onFailure(call: Call<ResponseGetUser>, t: Throwable) {

            }
        })
        }
    }

