package net.lyxnx.simplerest.response

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

internal class NetworkResponseAdapter<R : Any, E : Any>(
    private val successType: Type,
    private val errorConverter: Converter<ResponseBody, E>
) : CallAdapter<R, Call<NetworkResponse<R, E>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<R>): Call<NetworkResponse<R, E>> =
        NetworkResponseCall(call, errorConverter)
}