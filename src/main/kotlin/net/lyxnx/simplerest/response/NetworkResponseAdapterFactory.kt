package net.lyxnx.simplerest.response

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * The call adapter factory to be registered with Retrofit that will adapt [NetworkResponse] suspend functions
 */
public class NetworkResponseAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // suspend functions wrap the response in a Call object
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        check(returnType is ParameterizedType) {
            "Return type must be parameterized as Call<NetworkResponse<Foo>> or Call<NetworkResponse<out Foo>>"
        }

        // get the response type inside the Call type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response is not NetworkResponse, then we can't handle this type
        if (getRawType(responseType) != NetworkResponse::class.java) {
            return null
        }

        check(responseType is ParameterizedType) {
            "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>"
        }

        val successBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(1, responseType)

        val errorBodyConverter =
            retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
    }
}