package com.godwpfh.myapplication.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.adapter.ProfileViewPagerAdpater
import com.godwpfh.myapplication.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var homeViewPagerAdpater: ProfileViewPagerAdpater


    private lateinit var email : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email= intent.getStringExtra("username").toString()
        Log.d(TAG,"HomeActivity - onCreate() called email: $email")

        Log.d(TAG,"HomeActivity - onCreate() called")

        initAdapter()
        initBottomNavigation()
    }

    private fun initBottomNavigation(){
        binding.viewPagerHome.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bottomNavHome.menu.getItem(position).isChecked=true
            }
        })
        binding.bottomNavHome.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile -> {
                    binding.viewPagerHome.currentItem= PROFILE_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.viewPagerHome.currentItem= HOME_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else-> { //카메라인 경우
                    binding.viewPagerHome.currentItem= CAMERA_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
    private fun initAdapter(){
            val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
            homeViewPagerAdpater= ProfileViewPagerAdpater(this)
            homeViewPagerAdpater.setFragments(fragmentList)
            binding.viewPagerHome.adapter=homeViewPagerAdpater
    }

    fun getUserEmail() : String = email

    companion object{
        const val PROFILE_FRAGMENT=0
        const val HOME_FRAGMENT=1
        const val CAMERA_FRAGMENT=2
    }

}