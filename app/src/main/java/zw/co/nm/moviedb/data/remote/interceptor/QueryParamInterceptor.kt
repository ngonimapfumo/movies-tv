package zw.co.nm.moviedb.data.remote.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import zw.co.nm.moviedb.BuildConfig
import java.io.IOException

object QueryParamInterceptor : Interceptor {
    private const val language = "en-US"
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            // .addQueryParameter("language", language)
            .build()
        val request: Request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}