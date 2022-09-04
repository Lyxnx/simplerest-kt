package net.lyxnx.simplerest.request

import io.reactivex.rxjava3.core.Observable
import net.lyxnx.simplerest.ApiInterface
import net.lyxnx.simplerest.RestSingletons
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ApiRequestTaskTest {

    @Test
    fun test() {
        RestSingletons.setApi(object : ApiInterface {})

        val task = object : ApiRequestTask<String, ApiInterface>() {
            override fun buildObservable(api: ApiInterface): Observable<String> {
                return Observable.just("test")
            }
        }

        assertNotNull(task.observable)
        task.observable.subscribe {
            assertEquals("test", it)
        }
    }

}