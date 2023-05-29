package net.lyxnx.simplerest.example

import net.lyxnx.simplerest.ApiInterface
import net.lyxnx.simplerest.example.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface ExampleApiInterface : ApiInterface {

    @GET("/user")
    suspend fun getUser(): User

    @POST("/user")
    suspend fun postUser(@Body user: User)

}