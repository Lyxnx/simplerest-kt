package net.lyxnx.simplerest.request

import io.reactivex.rxjava3.core.Observable

/**
 * Represents a request task that returns some data
 * @param T type of data to return
 */
abstract class RequestTask<T : Any> : RxRequest<T> {

    override val observable by lazy {
        buildObservable()
    }

    /**
     * Build the observable for this request
     */
    abstract fun buildObservable(): Observable<T>

}