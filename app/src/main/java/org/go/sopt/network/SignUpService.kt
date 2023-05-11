package org.go.sopt.network

import org.go.sopt.model.RequestSignInDto
import org.go.sopt.model.RequestSignUpDto
import org.go.sopt.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignUpService {
    @POST("sign-up")
    fun signUp(
        @Body request : RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @GET("info/{userId}")
    fun checkUserInfo(
        @Path("userId") userId : String
    ): Call<ResponseSignUpDto>

}

interface SignInService {
    @POST("sign-in")
    fun signIn(
        @Body request: RequestSignInDto,
    ) : Call<ResponseSignUpDto>
}
