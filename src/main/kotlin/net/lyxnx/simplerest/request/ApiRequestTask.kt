package net.lyxnx.simplerest.request

import io.reactivex.rxjava3.core.Observable
import net.lyxnx.simplerest.ApiInterface
import net.lyxnx.simplerest.RestSingletons

/**
 * Represents an API request task
 * @param T type of data to be returned by the task
 * @param A the API to be used
 */
abstract class ApiRequestTask<T : Any, A : ApiInterface> : RequestTask<T>() {

    private val api = RestSingletons.getApi<A>()

    override fun buildObservable(): Observable<T> {
        return buildObservable(api)
    }

    /**
     * Builds the observable with the given API instance
     */
    protected abstract fun buildObservable(api: A): Observable<T>

}