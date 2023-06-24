package zw.co.nm.moviedb.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zw.co.nm.moviedb.data.remote.interceptor.ApiCallInterceptor
import zw.co.nm.moviedb.data.remote.service.MovieService
import zw.co.nm.moviedb.utils.Constants
import java.util.concurrent.TimeUnit


object NetworkManager {
    private val gson: Gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(ApiCallInterceptor)
        .addInterceptor(loggingInterceptor)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    var movieService: MovieService = retrofit.create(MovieService::class.java)

}