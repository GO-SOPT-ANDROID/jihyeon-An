package org.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.seminar1.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import org.go.sopt.network.SignUpService

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val signUpService = ServicePool.signUpService

    private lateinit var id : String
    private lateinit var pw :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(id : String, pw : String) {

    }


}