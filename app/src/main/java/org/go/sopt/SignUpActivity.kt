package org.go.sopt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.seminar1.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import org.go.sopt.model.RequestSignUpDto
import org.go.sopt.model.ResponseSignUpDto
import org.go.sopt.viewmodel.SignUpViewModel
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.isEnabled = false
        viewModel = SignUpViewModel()
        binding.viewModel = viewModel

        checkSignUpId()
        checkSignUpPw()
        checkBtnState()

        viewModel.signUpResult.observe(this) { signUpResult ->
            startActivity(
                Intent(this@SignUpActivity, LoginActivity::class.java)
            )
        }

        binding.btnSignUp.setOnClickListener {
            //clickSignUpButton()
            viewModel.signUp(
                binding.editTextId.text.toString(),
                binding.editTextPw.text.toString(),
                binding.editTextName.text.toString(),
                binding.editTextSkill.text.toString()
            )
        }
        //키보드 숨기기
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }
    private fun checkSignUpId(){
        viewModel.id.observe(this) {
            if (viewModel.checkId(it)) {
                binding.textInputLayoutId.error = null
            } else {
                binding.textInputLayoutId.error = "ID:영문과 숫자가 포함된 6~10글자 이내"
            }
            viewModel.setButtonState()
        }
    }
    private fun checkSignUpPw() {
        viewModel.pw.observe(this) {
            if(viewModel.checkPw(it)){
                binding.textInputLayoutPw.error = null
            } else {
                binding.textInputLayoutPw.error = "PW:영문,숫자,특수문자가 포함된 6~12글자 이내"
            }
            viewModel.setButtonState()
        }

    }
    private fun checkBtnState() {
        viewModel.btnState.observe(this) {
            binding.btnSignUp.isEnabled = it
        }
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