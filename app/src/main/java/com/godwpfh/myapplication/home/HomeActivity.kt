package com.godwpfh.myapplication.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()

    }

    private fun initTransactionEvent(){

         val followFragment = FollowFragment()
        val reposFragment = ReposFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_home, reposFragment).commit()

        binding.buttonFollow.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_home, followFragment).commit()
            Log.d(TAG,"HomeActivity - initTransactionEvent() called follow")
        }
        binding.buttonRepos.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_home, reposFragment).commit()
            Log.d(TAG,"HomeActivity - initTransactionEvent() called repos")
        }

    }

}