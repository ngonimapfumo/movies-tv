package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class CreateSessionResponse(
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("username")
    val username: String
)