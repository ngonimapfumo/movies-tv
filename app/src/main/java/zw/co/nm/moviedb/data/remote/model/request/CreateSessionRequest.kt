package zw.co.nm.moviedb.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class CreateSessionRequest(
    @SerializedName("request_token")
    val requestToken: String
)
