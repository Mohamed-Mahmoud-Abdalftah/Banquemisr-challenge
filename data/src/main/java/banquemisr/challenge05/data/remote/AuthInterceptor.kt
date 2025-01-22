package banquemisr.challenge05.data.remote

import banquemisr.challenge05.core.utils.Constants.TOKEN_API
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token =TOKEN_API

        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token") // Add token to headers
            .build()

        return chain.proceed(newRequest)
    }
}
