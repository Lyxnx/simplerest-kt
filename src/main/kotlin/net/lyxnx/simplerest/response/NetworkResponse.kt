@file:OptIn(ExperimentalContracts::class)

package net.lyxnx.simplerest.response

import retrofit2.Response
import java.io.IOException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Represents a network response
 *
 * This must be used with a suspend function within the Retrofit rest API and [NetworkResponseAdapterFactory]
 * registered during Retrofit creation
 *
 * @param R the body type of a successful response, could be a POJO type model to be parsed by a converter such as GSON
 * @param E the body type of an unsuccessful error response
 */
public sealed interface NetworkResponse<out R : Any, out E : Any> {

    /**
     * The raw response from the network
     *
     * If a network exception occurs, this will be null
     */
    public val response: Response<*>?

    /**
     * Represents a successful response with a body
     *
     * @property body The converted body from this response
     */
    public data class Success<R : Any>(override val response: Response<*>?, val body: R) :
        NetworkResponse<R, Nothing>

    /**
     * Represents a failure response with a body content
     *
     * @property code The status code returned from the network
     * @property body The converter error body from this response
     */
    public data class ApiError<E : Any>(
        override val response: Response<*>?,
        val code: Int,
        val body: E
    ) : NetworkResponse<Nothing, E>

    /**
     * Represents a network error
     *
     * @property error The IO exception that caused this network error
     */
    public data class NetworkError(override val response: Response<*>?, val error: IOException) :
        NetworkResponse<Nothing, Nothing>

    /**
     * Represents an error that is neither an [ApiError] nor a [NetworkError]
     *
     * @property error the throwable that caused this error
     */
    public data class UnknownError(override val response: Response<*>?, val error: Throwable?) :
        NetworkResponse<Nothing, Nothing>

}

/**
 * Checks whether this response is a successful response with a body
 */
public fun <R : Any, E : Any> NetworkResponse<R, E>.isSuccessful(): Boolean {
    contract {
        returns(true) implies (this@isSuccessful is NetworkResponse.Success<R>)
    }

    return this is NetworkResponse.Success
}

/**
 * Checks whether this response is an API error response - ie has a body
 */
public fun <R : Any, E : Any> NetworkResponse<R, E>.isApiError(): Boolean {
    contract {
        returns(true) implies (this@isApiError is NetworkResponse.ApiError<E>)
    }

    return this is NetworkResponse.ApiError
}

/**
 * Checks whether this response is a network error
 */
public fun <R : Any, E : Any> NetworkResponse<R, E>.isNetworkError(): Boolean {
    contract {
        returns(true) implies (this@isNetworkError is NetworkResponse.NetworkError)
    }

    return this is NetworkResponse.NetworkError
}

/**
 * Checks whether this response is some unknown error
 */
public fun <R : Any, E : Any> NetworkResponse<R, E>.isUnknownError(): Boolean {
    contract {
        returns(true) implies (this@isUnknownError is NetworkResponse.UnknownError)
    }

    return this is NetworkResponse.UnknownError
}