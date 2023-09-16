package io.github.lyxnx.simplerest.request

/**
 * Represents a request task that might return some data
 * @param T type of data to return
 */
public abstract class RequestTask<T> {

    /**
     * Executes this request and returns the response data (if any)
     */
    public abstract suspend fun execute(): T

}