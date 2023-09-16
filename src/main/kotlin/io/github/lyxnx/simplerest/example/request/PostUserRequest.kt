package io.github.lyxnx.simplerest.example.request

import io.github.lyxnx.simplerest.example.ExampleApiInterface
import io.github.lyxnx.simplerest.example.model.User

internal class PostUserRequest(private val user: User) : ExampleBaseRequest<Unit>() {

    override suspend fun execute(api: ExampleApiInterface) {
        api.postUser(user)
    }
}