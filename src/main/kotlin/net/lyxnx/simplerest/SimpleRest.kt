package net.lyxnx.simplerest

import kotlin.properties.Delegates

@Suppress("UNCHECKED_CAST")
public object SimpleRest {
    private var provider by Delegates.notNull<ApiProvider<*>>()
    private var api by Delegates.notNull<ApiInterface>()

    public fun <A : ApiInterface> init(provider: ApiProvider<A>) {
        this.provider = provider
        this.api = provider.build()
    }

    public fun <A : ApiInterface> api(): A {
        return api as A
    }
}