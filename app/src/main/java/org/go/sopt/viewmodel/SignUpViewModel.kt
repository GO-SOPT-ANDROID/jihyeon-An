package org.go.sopt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.go.sopt.ServicePool.signUpService
import org.go.sopt.model.RequestSignUpDto
import org.go.sopt.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val skill = MutableLiveData<String>()

    val btnState: MutableLiveData<Boolean> = MutableLiveData(false)

    fun signUp(id: String, password: String, name: String, skill: String) {
        signUpService.signUp(
            RequestSignUpDto(
                id,
                password,
                name,
                skill,
            )
        ).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
   fun setButtonState() {
        val signUpState = checkId(id.value) && checkPw(pw.value) && id.value?.isNotEmpty() == true && pw.value?.isNotEmpty() == true
        btnState.value = signUpState
    }

    fun checkId(id: String?): Boolean {
        return id.isNullOrEmpty() || id.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}\$".toRegex())
    }

    fun checkPw(pw: String?): Boolean {
        return pw.isNullOrEmpty() || pw.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*?])[A-Za-z[0-9]!@#\$%^&*?]{6,12}$".toRegex())
    }
}