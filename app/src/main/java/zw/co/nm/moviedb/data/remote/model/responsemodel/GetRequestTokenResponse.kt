package zw.co.nm.moviedb.data.remote.model.responsemodel


import com.google.gson.annotations.SerializedName

data class GetRequestTokenResponse(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)