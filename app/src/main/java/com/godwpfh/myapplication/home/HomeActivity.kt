package com.godwpfh.myapplication.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.ActivityHomeBinding
import com.godwpfh.myapplication.home.adapter.HomeViewPagerAdpater


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var homeViewPagerAdpater: HomeViewPagerAdpater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG,"HomeActivity - onCreate() called")
        
        //initTransactionEvent()

        initAdapter()
        initBottomNavigation()
    }

//    private fun initTransactionEvent(){
//
//         val followFragment = FollowFragment()
//        val reposFragment = ReposFragment()
//
//        supportFragmentManager.beginTransaction().add(R.id.fragment_home, followFragment).commit()
//
//        binding.buttonFollow.setOnClickListener {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_home, followFragment).commit()
//
//        }
//        binding.buttonRepos.setOnClickListener {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_home, reposFragment).commit()
//        }
//
//    }
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
            val fragmentList = listOf(ProfileFragment(), HomeFragment())
            homeViewPagerAdpater= HomeViewPagerAdpater(this)
            homeViewPagerAdpater.setFragments(fragmentList)
            binding.viewPagerHome.adapter=homeViewPagerAdpater
    }

    companion object{
        const val PROFILE_FRAGMENT=0
        const val HOME_FRAGMENT=1
        const val CAMERA_FRAGMENT=2
    }

}