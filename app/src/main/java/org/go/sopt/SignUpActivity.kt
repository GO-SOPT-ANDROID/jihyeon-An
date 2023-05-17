package org.go.sopt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.seminar1.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import org.go.sopt.model.RequestSignUpDto
import org.go.sopt.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpService = ServicePool.signUpService

    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String
    private lateinit var skill: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            clickSignUpButton()
        }

        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun clickSignUpButton() {

        id = binding.idEditTv.text.toString()
        pw = binding.pwEditTv.text.toString()
        name = binding.nameEditTv.text.toString()
        skill = binding.skillEditText.text.toString()

        val idState = id.length in 6..10
        val pwState = pw.length in 8..12
        val nameState = name.isNotEmpty()
        val skillState = skill.isNotEmpty()

        if (!idState) {
            makeSnackBar("아이디는 6~10글자 입니다.")
        } else if (!pwState) {
            makeSnackBar("비밀번호는 8~12글자 입니다.")
        } else {
            if (nameState && skillState) {
                completeSignUp()
            } else {
                makeSnackBar("모든 정보를 입력해주세요.")
            }
        }
    }

    private fun completeSignUp() {
        signUpService.signUp(
            RequestSignUpDto(
                id,
                pw,
                name,
                skill
            )
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                Log.e("SignUpActivity", "${response.code()}")
                //결과 코드가 200의 범위인 경우
                if (response.isSuccessful) {
                    Log.e("SignUpActivity", "회원가입에 성공하였습니다.")
                    makeSnackBar("회원가입에 성공하였습니다.")
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                    if (!isFinishing) finish()
                } else {
                    // 실패한 응답
                    if (response.code() == 409) {
                        Log.e("SignUpActivity", "중복된 아이디가 존재합니다.")
                        makeSnackBar("중복된 아이디가 존재합니다.")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                // 왜 안 오노
                t.message?.let { makeSnackBar(it) } ?: "서버통신 실패(응답값 X)"
            }
        })
    }


    private fun makeSnackBar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun hideKeyboard() {
        if (this.currentFocus != null) {
            val inputManager: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}