package org.go.sopt

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.go.sopt.network.ReqresService
import org.go.sopt.network.SignInService
import org.go.sopt.network.SignUpService
import retrofit2.Retrofit

object ReqresApiFactory {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}
object ReqresServicePool {
    val reqresService = ReqresApiFactory.create<ReqresService>()

}