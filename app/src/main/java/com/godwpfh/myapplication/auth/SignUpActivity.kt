package com.godwpfh.myapplication.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.godwpfh.myapplication.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFinishSignup.setOnClickListener {
            if (binding.edittextName.text.isEmpty() || binding.edittextSignupId.text.isEmpty() || binding.edittextSignupPw.text.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, SignInActivity::class.java).apply{
                putExtra("id", binding.edittextSignupId.text.toString())
                putExtra("pw", binding.edittextSignupPw.text.toString())
            }

            setResult(RESULT_OK, intent)

            finish()


        }
    }
}