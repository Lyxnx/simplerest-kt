package io.github.lyxnx.simplerest.request

import io.github.lyxnx.simplerest.ApiInterface
import io.github.lyxnx.simplerest.SimpleRest

/**
 * Represents an API request task
 * @param T type of data to be returned by the task
 * @param A the API to be used
 */
public abstract class ApiRequestTask<T : Any, A : ApiInterface> : RequestTask<T>() {

    private val api = SimpleRest.api<A>()

    override suspend fun execute(): T {
        return execute(api)
    }

    /**
     * Gets the response with the given API instance
     */
    protected abstract suspend fun execute(api: A): T

}