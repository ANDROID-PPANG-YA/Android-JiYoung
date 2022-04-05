package com.godwpfh.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.godwpfh.myapplication.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.buttonLogin.setOnClickListener {

        }
    }
}