package io.github.lyxnx.simplerest.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import io.github.lyxnx.simplerest.SimpleRest
import io.github.lyxnx.simplerest.example.request.GetUserRequest
import io.github.lyxnx.simplerest.example.request.PostUserRequest
import io.github.lyxnx.simplerest.sendRequest

internal class Example {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        SimpleRest.init(ExampleApiProvider)
    }

    fun performRequest() {
        scope.launch {
            val user = GetUserRequest().execute()

            PostUserRequest(user).execute()

            val user2 = ExampleApiProvider.sendRequest {
                getUser()
            }
        }
    }

}