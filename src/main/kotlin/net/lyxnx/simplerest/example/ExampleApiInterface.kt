package net.lyxnx.simplerest.example

import io.reactivex.rxjava3.core.Observable
import net.lyxnx.simplerest.ApiInterface
import retrofit2.http.Body
import retrofit2.http.GET

interface ExampleApiInterface : ApiInterface {

    @GET("/test")
    fun callTest(@Body testString: String): Observable<Int>

}