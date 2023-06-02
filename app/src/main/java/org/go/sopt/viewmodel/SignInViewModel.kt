package org.go.sopt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.go.sopt.ServicePool.signInService
import org.go.sopt.model.RequestSignInDto
import org.go.sopt.model.ResponseSignInDto
import org.go.sopt.network.SignInService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    val signInResult: MutableLiveData<ResponseSignInDto> = MutableLiveData()

    fun signIn(id: String, password: String) {
        signInService.signIn(
            RequestSignInDto(
                id,
                password
            )
        ).enqueue(object : Callback<ResponseSignInDto> {
            override fun onResponse(
                call: Call<ResponseSignInDto>,
                response: Response<ResponseSignInDto>
            ) {
                if(response.isSuccessful){
                    signInResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}