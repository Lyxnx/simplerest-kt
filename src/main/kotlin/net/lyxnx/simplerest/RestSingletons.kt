package net.lyxnx.simplerest

object RestSingletons {

    private var api: ApiInterface? = null

    /**
     * Gets the stored API instance, casting to the given type
     *
     * Note that if the API has not been set yet, an [IllegalStateException] will be thrown
     */
    fun <T : ApiInterface> getApi(): T {
        if (api == null) {
            throw IllegalStateException("API instance has not been set yet. Use setApi(api) first")
        }

        return api as T
    }

    /**
     * Sets the stored API instance
     */
    fun <T : ApiInterface> setApi(api: T?) {
        RestSingletons.api = api
    }

}