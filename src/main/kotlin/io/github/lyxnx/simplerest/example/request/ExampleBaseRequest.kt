package io.github.lyxnx.simplerest.example.request

import io.github.lyxnx.simplerest.example.ExampleApiInterface
import io.github.lyxnx.simplerest.request.ApiRequestTask

internal abstract class ExampleBaseRequest<T : Any> : ApiRequestTask<T, ExampleApiInterface>()