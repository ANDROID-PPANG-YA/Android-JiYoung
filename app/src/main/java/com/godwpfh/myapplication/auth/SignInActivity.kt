package com.godwpfh.myapplication.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.godwpfh.myapplication.home.HomeActivity
import com.godwpfh.myapplication.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->

                val intent: Intent? = it.data
                val signup_id = intent?.getStringExtra("id").toString()
                val signup_pw = intent?.getStringExtra("pw").toString()
                if (it.resultCode == RESULT_OK) {
                    binding.edittextId.setText(signup_id)
                    binding.edittextPw.setText(signup_pw)
                }
            }

        binding.buttonSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }
        binding.buttonLogin.setOnClickListener {
            if (binding.edittextId.text.isEmpty() || binding.edittextPw.text.isEmpty()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}