package com.godwpfh.myapplication.ui.home.profile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.godwpfh.myapplication.R
import com.godwpfh.myapplication.data.FollowData
import com.godwpfh.myapplication.databinding.ActivityDetailBinding
import com.godwpfh.myapplication.databinding.ActivitySignUpBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data= intent.getSerializableExtra("follow_data") as FollowData
        binding.textviewDetail.text=data.follow_name
        Glide.with(binding.root)
            .load(data.follow_image)
            .into(binding.imageviewDetail)
    }
}