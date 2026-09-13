package zw.co.nm.moviedb.data.remote.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.data.remote.interceptor.QueryParamInterceptor
import zw.co.nm.moviedb.data.remote.service.AccountService
import zw.co.nm.moviedb.data.remote.service.ApiServiceGeneral
import zw.co.nm.moviedb.data.remote.service.AuthService
import zw.co.nm.moviedb.data.remote.service.CollectionService
import zw.co.nm.moviedb.data.remote.service.ConfigService
import zw.co.nm.moviedb.data.remote.service.ListService
import zw.co.nm.moviedb.data.remote.service.MovieService
import zw.co.nm.moviedb.data.remote.service.TvShowService
import zw.co.nm.moviedb.util.Constants
import java.util.concurrent.TimeUnit

object NetworkManager {
    private val gson: Gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .create()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(QueryParamInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                // Logging only on debug builds so release DEX can drop the interceptor.
                val logging = okhttp3.logging.HttpLoggingInterceptor().apply {
                    level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(logging)
            }
        }
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
    var accountService: AccountService = retrofit.create(AccountService::class.java)
    var configService: ConfigService = retrofit.create(ConfigService::class.java)
    var apiServiceGeneral: ApiServiceGeneral = retrofit.create(ApiServiceGeneral::class.java)
    var listService: ListService = retrofit.create(ListService::class.java)
}
