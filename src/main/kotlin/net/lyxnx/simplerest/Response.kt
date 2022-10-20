package net.lyxnx.simplerest

sealed class Response<out T> {

    class Success<T>(val data: T) : Response<T>()
    object Loading : Response<Nothing>()
    class Error(val throwable: Throwable) : Response<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(throwable: Throwable) = Error(throwable)
        fun loading() = Loading
    }

    val isLoading: Boolean
        get() = this is Loading

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Error

    fun getOrNull(): T? = when (this) {
        is Success -> this.data
        else -> null
    }

    fun throwableOrNull(): Throwable? = when (this) {
        is Error -> this.throwable
        else -> null
    }

}