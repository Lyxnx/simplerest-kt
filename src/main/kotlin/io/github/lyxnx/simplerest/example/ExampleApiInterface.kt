package io.github.lyxnx.simplerest.example

import io.github.lyxnx.simplerest.ApiInterface
import io.github.lyxnx.simplerest.example.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface ExampleApiInterface : ApiInterface {

    @GET("/user")
    suspend fun getUser(): User

    @POST("/user")
    suspend fun postUser(@Body user: User)

}