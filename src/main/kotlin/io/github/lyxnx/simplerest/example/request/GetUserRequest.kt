package io.github.lyxnx.simplerest.example.request

import io.github.lyxnx.simplerest.example.ExampleApiInterface
import io.github.lyxnx.simplerest.example.model.User

internal class GetUserRequest : ExampleBaseRequest<User>() {

    override suspend fun execute(api: ExampleApiInterface): User {
        return api.getUser()
    }

}