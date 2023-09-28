package zw.co.nm.moviedb.data.remote.util
import retrofit2.Response

data class Response<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {

    companion object {

        fun <T> success(data: Response<T>): zw.co.nm.moviedb.data.remote.util.Response<T> {
            return Response(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): zw.co.nm.moviedb.data.remote.util.Response<T> {
            return Response(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }


    sealed class Status {
        object Success : Status()
        object Failure : Status()

    }

    val failed: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful == true

    val loading: Boolean
        get() = !failed && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!

}