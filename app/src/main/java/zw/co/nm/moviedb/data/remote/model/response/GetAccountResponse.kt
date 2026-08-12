package zw.co.nm.moviedb.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class GetAccountResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("username")
    val username: String
)
