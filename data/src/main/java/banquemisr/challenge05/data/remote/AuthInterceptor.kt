package banquemisr.challenge05.data.remote

import banquemisr.challenge05.core.utils.Constants.AUTHORIZATION_HEADER
import banquemisr.challenge05.core.utils.Constants.TOKEN_API
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = TOKEN_API

        val newRequest = chain.request()
            .newBuilder()
            .addHeader(AUTHORIZATION_HEADER, "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }

}
