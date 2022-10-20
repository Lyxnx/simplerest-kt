package net.lyxnx.simplerest.example

import net.lyxnx.simplerest.ApiInterface
import retrofit2.http.Body
import retrofit2.http.GET

interface ExampleApiInterface : ApiInterface {

    @GET("/test")
    suspend fun callTest(@Body testString: String): Int

}