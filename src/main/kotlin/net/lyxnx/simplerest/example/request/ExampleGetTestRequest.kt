package net.lyxnx.simplerest.example.request

import io.reactivex.rxjava3.core.Observable
import net.lyxnx.simplerest.example.ExampleApiInterface

class ExampleGetTestRequest : ExampleBaseRequest<Int>() {

    override fun buildObservable(api: ExampleApiInterface): Observable<Int> {
        return api.callTest("Some request body")
    }
}