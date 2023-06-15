package zw.co.nm.moviedb.data.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import zw.co.nm.moviedb.BuildConfig
import java.io.IOException

object ApiCallInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request()
            .url
            .newBuilder()
            //storing api key in local.properties file for now
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", "en-US")
            .build()
        val request: Request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}