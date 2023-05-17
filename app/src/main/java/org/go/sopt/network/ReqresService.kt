package org.go.sopt.network

import org.go.sopt.model.RequestSignInDto
import org.go.sopt.model.ResponseSignInDto
import org.go.sopt.model.ResponseUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReqresService {
    @GET("api/users")
    fun getUser(
        @Query("page") page : Int
    ) : Call<ResponseUser>
}