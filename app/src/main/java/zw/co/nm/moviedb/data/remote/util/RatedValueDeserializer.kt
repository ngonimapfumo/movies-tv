package zw.co.nm.moviedb.data.remote.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/** TMDB `rated` is either `false` or `{ "value": 8.0 }`. */
class RatedValueDeserializer : JsonDeserializer<Double?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Double? {
        if (json == null || json.isJsonNull) return null
        if (json.isJsonPrimitive) {
            val primitive = json.asJsonPrimitive
            if (primitive.isBoolean) return null
            if (primitive.isNumber) return primitive.asDouble
            return null
        }
        if (json.isJsonObject) {
            val value = json.asJsonObject.get("value") ?: return null
            return if (value.isJsonPrimitive && value.asJsonPrimitive.isNumber) {
                value.asDouble
            } else {
                null
            }
        }
        return null
    }
}
