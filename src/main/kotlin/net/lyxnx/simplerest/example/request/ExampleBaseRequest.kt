package net.lyxnx.simplerest.example.request

import net.lyxnx.simplerest.example.ExampleApiInterface
import net.lyxnx.simplerest.request.ApiRequestTask

abstract class ExampleBaseRequest<T : Any> : ApiRequestTask<T, ExampleApiInterface>()