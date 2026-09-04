package zw.co.nm.moviedb.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CreateListResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("list_id")
    val listId: Int
)

data class GetAccountListsResponse(
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
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("favorite_count")
        val favoriteCount: Int? = null,
        @SerializedName("id")
        val id: Int,
        @SerializedName("item_count")
        val itemCount: Int? = null,
        @SerializedName("iso_639_1")
        val iso6391: String? = null,
        @SerializedName("list_type")
        val listType: String? = null,
        @SerializedName("name")
        val name: String,
        @SerializedName("poster_path")
        val posterPath: String? = null
    )
}

data class GetListDetailResponse(
    @SerializedName("created_by")
    val createdBy: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("favorite_count")
    val favoriteCount: Int? = null,
    @SerializedName("id")
    val id: String,
    @SerializedName("items")
    val items: List<GetPopularMoviesListResponse.Result> = emptyList(),
    @SerializedName("item_count")
    val itemCount: Int? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String? = null
)
