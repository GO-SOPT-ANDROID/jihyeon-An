package org.go.sopt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.seminar1.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding

    private val id = binding.idEditTv.text
    private val pw = binding.pwEditTv.text
    private val name = binding.nameEditTv.text
    private val talent = binding.talentEditTv.text

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()

        binding.root.setOnClickListener {
            hideKeyboard()
        }

    }

    private fun signUp(){
        binding.signUpBtn.setOnClickListener {

            if(id.length < 6){
                Snackbar.make(
                    binding.root,
                    "아이디는 6~10글자 이내입니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else {
                if (pw.length < 8){
                    Snackbar.make(
                        binding.root,
                        "비밀번호는 8~12글자 이내입니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("id", id.toString())
                    intent.putExtra("pw", pw.toString())
                    intent.putExtra("name", name.toString())
                    intent.putExtra("talent", talent.toString())

                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    private fun hideKeyboard(){
        if(this.currentFocus != null){
            val inputManager : InputMethodManager =this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}