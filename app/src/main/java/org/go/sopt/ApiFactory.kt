package org.go.sopt

import com.example.seminar1.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.go.sopt.interceptor.AuthInterceptor
import org.go.sopt.network.SignInService
import org.go.sopt.network.SignUpService
import retrofit2.Retrofit

object ApiFactory {

    private const val AUTH_BASE_URL = BuildConfig.AUTH_BASE_URL

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }).build()
    }

    val retrofitForAuth : Retrofit by lazy {
        Retrofit.Builder().baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    inline fun <reified T> createAuthService(): T = retrofitForAuth.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = ApiFactory.createAuthService<SignUpService>()
    val signInService = ApiFactory.createAuthService<SignInService>()
}