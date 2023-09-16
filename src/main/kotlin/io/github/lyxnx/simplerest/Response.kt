package io.github.lyxnx.simplerest

public sealed class Response<out T> {

    public class Success<T>(public val data: T) : Response<T>()
    public object Loading : Response<Nothing>()
    public class Error(public val throwable: Throwable) : Response<Nothing>()

    public companion object {
        public fun <T> success(data: T): Success<T> = Success(data)
        public fun error(throwable: Throwable): Error = Error(throwable)
        public fun error(message: String): Error = Error(Exception(message))
        public fun loading(): Loading = Loading
    }

    public val isLoading: Boolean
        get() = this is Loading

    public val isSuccess: Boolean
        get() = this is Success

    public val isFailure: Boolean
        get() = this is Error

    public fun getOrNull(): T? = when (this) {
        is Success -> this.data
        else -> null
    }

    public fun get(): T = getOrNull() ?: throw IllegalStateException("Response type is not success")

    public fun throwableOrNull(): Throwable? = when (this) {
        is Error -> this.throwable
        else -> null
    }

    public fun throwable(): Throwable =
        throwableOrNull() ?: throw IllegalStateException("Response type is not failure")

}