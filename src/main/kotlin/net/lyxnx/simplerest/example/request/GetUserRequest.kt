package net.lyxnx.simplerest.example.request

import net.lyxnx.simplerest.example.ExampleApiInterface
import net.lyxnx.simplerest.example.model.User

class GetUserRequest : ExampleBaseRequest<User>() {

    override suspend fun execute(api: ExampleApiInterface): User {
        return api.getUser()
    }

}