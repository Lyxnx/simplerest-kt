package net.lyxnx.simplerest.example

import net.lyxnx.simplerest.ApiProvider
import net.lyxnx.simplerest.buildApi

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