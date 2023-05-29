package net.lyxnx.simplerest.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import net.lyxnx.simplerest.SimpleRest
import net.lyxnx.simplerest.example.request.GetUserRequest
import net.lyxnx.simplerest.example.request.PostUserRequest
import net.lyxnx.simplerest.sendRequest

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