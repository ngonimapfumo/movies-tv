package zw.co.nm.moviedb.data.remote.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.data.remote.interceptor.QueryParamInterceptor
import zw.co.nm.moviedb.data.remote.service.ApiServiceGeneral
import zw.co.nm.moviedb.data.remote.service.AuthService
import zw.co.nm.moviedb.data.remote.service.CollectionService
import zw.co.nm.moviedb.data.remote.service.ConfigService
import zw.co.nm.moviedb.data.remote.service.MovieService
import zw.co.nm.moviedb.data.remote.service.TvShowService
import zw.co.nm.moviedb.util.Constants
import java.util.concurrent.TimeUnit


object NetworkManager {
    private val gson: Gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = when {
            BuildConfig.DEBUG -> {
                HttpLoggingInterceptor.Level.BODY
            }

            else -> {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(QueryParamInterceptor)/*Interceptor { chain: Interceptor.Chain ->
            Response
            val url: HttpUrl = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language", "lang")
                .build()
            val request: Request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }*/
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
    var tvShowService: TvShowService = retrofit.create(TvShowService::class.java)
    var collectionService: CollectionService = retrofit.create(CollectionService::class.java)
    var authService: AuthService = retrofit.create(AuthService::class.java)
    var configService: ConfigService = retrofit.create(ConfigService::class.java)
    var apiServiceGeneral: ApiServiceGeneral = retrofit.create(ApiServiceGeneral::class.java)

}