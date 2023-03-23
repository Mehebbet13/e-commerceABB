package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RetroClientInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()
        var request = chain.request()
        request =
            request.newBuilder().header("Authorization", "$token")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()
        return chain.proceed(request)
    }
}
