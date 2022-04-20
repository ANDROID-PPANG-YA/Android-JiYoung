package com.godwpfh.myapplication.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.ActivityHomeBinding
import kotlin.math.log

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    private val followFragment = FollowFragment()
    private val reposFragment = ReposFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragment_home, followFragment).commit()

        initTransactionEvent()

    }

    private fun initTransactionEvent(){

        binding.buttonFollow.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            //Log.d(TAG,"HomeActivity - initTransactionEvent() called , follow로 바뀜 ")
            transaction.replace(R.id.fragment_home, followFragment)
            transaction.commit()
        }
        binding.buttonRepos.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            //Log.d(TAG,"HomeActivity - initTransactionEvent() called , repos로 바뀜 ")
            transaction.replace(R.id.fragment_home, reposFragment)
            transaction.commit()
        }

    }

}