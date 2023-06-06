package zw.co.nm.moviedb.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        .readTimeout(5, TimeUnit.MINUTES)
        .connectTimeout(5, TimeUnit.MINUTES)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    var apiService: ApiService = retrofit.create(ApiService::class.java)

}