package net.lyxnx.simplerest

/**
 * Represents a provider of an [ApiInterface]
 * @param A type of API interface
 */
interface ApiProvider<A : ApiInterface> {

    /**
     * Create the API interface using retrofit or another HTTP API
     */
    fun build(): A

}