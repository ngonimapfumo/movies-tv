package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

class GetCountriesResponse : ArrayList<GetCountriesResponse.GetCountriesResponseItem>() {
    data class GetCountriesResponseItem(
        @SerializedName("english_name")
        val englishName: String,
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("native_name")
        val nativeName: String
    )
}