package zw.co.nm.moviedb.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CreateSessionIdResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("session_id")
    val sessionId: String
)
