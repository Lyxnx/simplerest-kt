package net.lyxnx.simplerest.request

/**
 * Represents a request task that might return some data
 * @param T type of data to return
 */
abstract class RequestTask<T> {

    /**
     * Executes this request and returns the response data (if any)
     */
    abstract suspend fun execute(): T

}