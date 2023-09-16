package io.github.lyxnx.simplerest.example

import io.github.lyxnx.simplerest.ApiProvider
import io.github.lyxnx.simplerest.buildApi

internal object ExampleApiProvider : ApiProvider<ExampleApiInterface> {

    override fun build(): ExampleApiInterface {
        return buildApi {
            baseUrl("https://test.com")
            client {
                addInterceptor {
                    val original = it.request()

                    it.proceed(
                        original.newBuilder()
                            .method(original.method(), original.body())
                            .header("User-Agent", "Some user agent")
                            .build()
                    )
                }
            }
        }
    }
}