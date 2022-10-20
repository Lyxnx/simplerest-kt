package net.lyxnx.simplerest.example.request

import net.lyxnx.simplerest.example.ExampleApiInterface

class ExampleGetTestRequest : ExampleBaseRequest<Int>() {

    override suspend fun getResponse(api: ExampleApiInterface): Int {
        return api.callTest("Some request body")
    }
}