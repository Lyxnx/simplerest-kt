package net.lyxnx.simplerest

import kotlin.properties.Delegates

@Suppress("UNCHECKED_CAST")
object SimpleRest {
    private var provider by Delegates.notNull<ApiProvider<*>>()
    private var api by Delegates.notNull<ApiInterface>()

    fun <A : ApiInterface> init(provider: ApiProvider<A>) {
        this.provider = provider
        this.api = provider.build()
    }

    fun <A : ApiInterface> api(): A {
        return api as A
    }
}