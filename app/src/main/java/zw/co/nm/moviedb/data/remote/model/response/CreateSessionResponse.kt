package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class CreateSessionResponse(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)