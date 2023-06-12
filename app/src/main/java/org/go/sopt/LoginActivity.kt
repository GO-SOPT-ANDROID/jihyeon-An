package org.go.sopt

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.seminar1.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import org.go.sopt.model.RequestSignInDto
import org.go.sopt.model.ResponseSignInDto
import org.go.sopt.network.SignInService
import org.go.sopt.network.SignUpService
import org.go.sopt.util.makeSnackBar
import org.go.sopt.viewmodel.SignInViewModel
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var viewModel: SignInViewModel

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        viewModel = SignInViewModel()

        //회원가입 화면으로 이동
        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //로그인 버튼 클릭
        binding.loginBtn.setOnClickListener {
            //completeSignIn()
            viewModel.signIn(
                binding.idEditTv.text.toString(),
                binding.pwEditTv.text.toString()
            )
        }

        viewModel.signInResult.observe(this) {signInResult ->
            editor.putString("id", signInResult.data.id)
            editor.apply()

            startActivity(
                Intent(this@LoginActivity, MainActivity::class.java)
            )
            finish()
        }

        autoLogin()
    }

    private fun autoLogin(){
        val autoId = sharedPreferences.getString("id",null)
        if(autoId != null){
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("id", autoId)
            }
            binding.root.makeSnackBar("자동로그인 되었습니다.")
            startActivity(intent)
            finish()
        }
    }
}