package com.godwpfh.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.godwpfh.myapplication.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFinishSignup.setOnClickListener {
            if(binding.edittextName.text.isEmpty() || binding.edittextSignupId.text.isEmpty() ||binding.edittextSignupPw.text.isEmpty()){
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("id",binding.edittextSignupId.text.toString())
            intent.putExtra("pw",binding.edittextSignupPw.text.toString())

            setResult(RESULT_OK, intent)

            finish()


        }
    }
}