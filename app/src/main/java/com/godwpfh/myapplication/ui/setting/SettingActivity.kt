package com.godwpfh.myapplication.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.godwpfh.myapplication.data.local.GithubSharedPreference
import com.godwpfh.myapplication.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAutoLoginClicked()
    }

    private fun initAutoLoginClicked() {
        binding.textviewLoginSetting.setOnClickListener {
            GithubSharedPreference.finishAutoLogin(this)
        }

    }
}