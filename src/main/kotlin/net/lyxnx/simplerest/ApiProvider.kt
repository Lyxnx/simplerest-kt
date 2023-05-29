package net.lyxnx.simplerest

/**
 * Represents a provider of an [ApiInterface]
 * @param A type of API interface
 */
public interface ApiProvider<A : ApiInterface> {

    /**
     * Create the API interface using retrofit or another HTTP API
     */
    public fun build(): A

}

// Require the receiver parameter otherwise the generics look ugly
@Suppress("UnusedReceiverParameter")
/**
 * Send and executes the request specified in [block]
 *
 * If [SimpleRest.init] has not been called, this will throw an [IllegalStateException]
 */
public suspend fun <A : ApiInterface, R> ApiProvider<A>.sendRequest(block: suspend A.() -> R): R {
    try {
        return block(SimpleRest.api())
    } catch (_: IllegalStateException) {
        throw IllegalStateException("SimpleRest api must be initialized with init() first")
    }
}