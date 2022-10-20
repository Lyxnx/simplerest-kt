package net.lyxnx.simplerest.request

/**
 * Represents a request task that returns some data
 * @param T type of data to return
 */
abstract class RequestTask<T> {

    /**
     * Gets the response from this request
     */
    abstract suspend fun getResponse(): T

}