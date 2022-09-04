package net.lyxnx.simplerest.request

import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RequestTaskTest {

    @Test
    fun lazyObservableShouldBeCreatedWhenCalling() {
        val task = object : RequestTask<String>() {
            override fun buildObservable(): Observable<String> {
                return Observable.just("test")
            }
        }

        assertNotNull(task.observable)
        task.observable.subscribe {
            assertEquals("test", it)
        }
    }

}