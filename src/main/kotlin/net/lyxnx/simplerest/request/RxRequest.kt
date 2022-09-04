package net.lyxnx.simplerest.request

import io.reactivex.rxjava3.core.Observable

/**
 * Represents an RX request that returns data wrapped in an [Observable]
 * @param T type of wrapped data
 */
interface RxRequest<T : Any> {

    /**
     * Gets the observable for this request
     */
    val observable: Observable<T>
}