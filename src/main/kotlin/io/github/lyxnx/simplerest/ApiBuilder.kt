package io.github.lyxnx.simplerest

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Build an API using Kotlin's DSL format.
 *
 * This will use Retrofit
 */
public inline fun <reified T : ApiInterface> buildApi(block: ApiBuilder.() -> Unit): T {
    return ApiBuilder().apply(block).build(T::class.java)
}

@DslMarker
internal annotation class ApiBuilderDsl

@ApiBuilderDsl
public class ApiBuilder {

    public companion object {
        /**
         * Default client with simple read and call timeouts set to 60 seconds
         */
        public val DEFAULT_CLIENT: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .build()

        /**
         * Default response converter factory which uses Gson for JSON response parsing
         */
        public val DEFAULT_CONVERTER_FACTORY: Converter.Factory = GsonConverterFactory.create()
    }

    private val builder = Retrofit.Builder()

    private var client: OkHttpClient? = null
    private var callAdapterFactory: CallAdapter.Factory? = null
    private var converterFactory: Converter.Factory? = null

    /**
     * Sets the base URL for this API
     *
     * This is required to be set
     */
    public fun baseUrl(url: String) {
        builder.baseUrl(url)
    }

    /**
     * Adjust HTTP client properties
     *
     * This is not required unless client properties would like to be adjusted from the defaults
     *
     * See [DEFAULT_CLIENT]
     */
    public fun client(block: OkHttpClient.Builder.() -> Unit) {
        client = OkHttpClient.Builder().apply(block).build()
    }

    /**
     * Sets the call adapter factory for requests
     *
     * This is not required unless a different adapter factory is required from default
     *
     * See [DEFAULT_CALL_ADAPTER_FACTORY]
     */
    public fun callAdapterFactory(factory: CallAdapter.Factory) {
        callAdapterFactory = factory
    }

    /**
     * Sets the converter factory for response bodies
     */
    public fun converterFactory(factory: Converter.Factory) {
        converterFactory = factory
    }

    /**
     * Builds the API using [clazz] as the API class
     *
     * * [baseUrl] must have been set
     * * If [client] has not been configured, [DEFAULT_CLIENT] will be used
     * * If [converterFactory] has not been called, [DEFAULT_CONVERTER_FACTORY] will be used
     */
    public fun <T : ApiInterface> build(clazz: Class<T>): T {
        builder.client(client ?: DEFAULT_CLIENT)
        callAdapterFactory?.let {
            builder.addCallAdapterFactory(it)
        }
        builder.addConverterFactory(converterFactory ?: DEFAULT_CONVERTER_FACTORY)

        return builder.build().create(clazz)
    }
}