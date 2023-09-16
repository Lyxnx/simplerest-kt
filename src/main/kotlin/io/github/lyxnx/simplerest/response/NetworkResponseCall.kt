package io.github.lyxnx.simplerest.response

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

internal class NetworkResponseCall<R : Any, E : Any>(
    private val delegate: Call<R>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<R, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<R, E>>) =
        delegate.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                val networkResponse = if (response.isSuccessful) {
                    if (body != null) NetworkResponse.Success(response, body)
                    else NetworkResponse.UnknownError(response, null)
                } else {
                    if (error != null) {
                        try {
                            val errorBody = errorConverter.convert(error)

                            if (errorBody != null) NetworkResponse.ApiError(
                                response,
                                code,
                                errorBody
                            )
                            else NetworkResponse.UnknownError(response, null)
                        } catch (t: Throwable) {
                            NetworkResponse.UnknownError(response, t)
                        }
                    } else {
                        NetworkResponse.UnknownError(response, null)
                    }
                }

                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(networkResponse)
                )
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.NetworkError(null, throwable)
                    else -> NetworkResponse.UnknownError(null, throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })

    override fun execute(): Response<NetworkResponse<R, E>> =
        throw UnsupportedOperationException("NetworkResponseCall does not support synchronous execute()")

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun clone(): Call<NetworkResponse<R, E>> =
        NetworkResponseCall(delegate.clone(), errorConverter)
}