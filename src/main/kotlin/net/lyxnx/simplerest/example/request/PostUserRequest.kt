package net.lyxnx.simplerest.example.request

import net.lyxnx.simplerest.example.ExampleApiInterface
import net.lyxnx.simplerest.example.model.User

internal class PostUserRequest(private val user: User) : ExampleBaseRequest<Unit>() {

    override suspend fun execute(api: ExampleApiInterface) {
        api.postUser(user)
    }
}