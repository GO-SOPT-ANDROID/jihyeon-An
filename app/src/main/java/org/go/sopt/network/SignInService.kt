package org.go.sopt.network

import org.go.sopt.model.RequestSignInDto
import org.go.sopt.model.ResponseSignInDto
import org.go.sopt.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignInService {
    @POST("sign-in")
    fun signIn(
        @Body request: RequestSignInDto,
    ) : Call<ResponseSignInDto>
}