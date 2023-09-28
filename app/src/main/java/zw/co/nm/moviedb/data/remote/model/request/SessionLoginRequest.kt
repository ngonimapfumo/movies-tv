package zw.co.nm.moviedb.data.remote.model.request


import com.google.gson.annotations.SerializedName

data class SessionLoginRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("username")
    val username: String
)