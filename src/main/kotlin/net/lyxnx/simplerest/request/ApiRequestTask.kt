package net.lyxnx.simplerest.request

import net.lyxnx.simplerest.ApiInterface
import net.lyxnx.simplerest.RestSingletons

/**
 * Represents an API request task
 * @param T type of data to be returned by the task
 * @param A the API to be used
 */
abstract class ApiRequestTask<T : Any, A : ApiInterface> : RequestTask<T>() {

    private val api = RestSingletons.getApi<A>()

    override suspend fun getResponse(): T {
        return getResponse(api)
    }

    /**
     * Gets the response with the given API instance
     */
    protected abstract suspend fun getResponse(api: A): T

}