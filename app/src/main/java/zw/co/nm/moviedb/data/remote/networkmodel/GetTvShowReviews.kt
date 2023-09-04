package zw.co.nm.moviedb.data.remote.networkmodel


import com.google.gson.annotations.SerializedName

data class GetTvShowReviews(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Result(
        @SerializedName("author")
        val author: String,
        @SerializedName("author_details")
        val authorDetails: AuthorDetails,
        @SerializedName("content")
        val content: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("url")
        val url: String
    ) {
        data class AuthorDetails(
            @SerializedName("avatar_path")
            val avatarPath: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("rating")
            val rating: Int,
            @SerializedName("username")
            val username: String
        )
    }
}