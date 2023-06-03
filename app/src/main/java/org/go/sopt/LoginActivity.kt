package org.go.sopt

import android.content.Intent
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
    private val signInService = ServicePool.signInService
    private lateinit var viewModel: SignInViewModel

    private lateinit var id : String
    private lateinit var pw :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = SignInViewModel()

        //회원가입 화면으로 이동
        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        
        binding.loginBtn.setOnClickListener {

//            id = binding.idEditTv.text.toString()
//            pw = binding.pwEditTv.text.toString()
            //completeSignIn()
            viewModel.signIn(
                binding.idEditTv.text.toString(),
                binding.pwEditTv.text.toString()
            )
        }

        viewModel.signInResult.observe(this) {signInResult ->
            startActivity(
                Intent(this@LoginActivity, MainActivity::class.java)
            )
        }
    }

    private fun completeSignIn() {
        signInService.signIn(
            RequestSignInDto(
                id,
                pw
            )
        ).enqueue(object: retrofit2.Callback<ResponseSignInDto> {
            override fun onResponse(
                call: Call<ResponseSignInDto>,
                response: Response<ResponseSignInDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let { binding.root.makeSnackBar(it) } ?: "로그인에 성공하였습니다."
                    Log.e("LoginActivity","로그인에 성공하였습니다")
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        .putExtra("userName", response.body()?.data?.name)
                        .putExtra("userSkill", response.body()?.data?.skill)
                    startActivity(intent)
                    finish()

                } else {
                    // 실패한 응답
                    binding.root.makeSnackBar("존재하지 않는 회원입니다.")
                    Log.e("LoginActivity","40X")
                }
            }

            override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                t.message?.let { binding.root.makeSnackBar(it) } ?: "서버통신 실패(응답값 X)"
                Log.e("LoginActivity",t.toString())
            }
        })
    }
}