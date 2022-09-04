package net.lyxnx.simplerest

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNotNull

class RestSingletonsTest {

    @AfterEach
    fun teardown() {
        RestSingletons.setApi(null)
    }

    @Test
    fun testNullApiThrowsError() {
        assertThrows<IllegalStateException> {
            RestSingletons.getApi<ApiInterface>()
        }
    }

    @Test
    fun testNotNullApiDoesNotThrowError() {
        RestSingletons.setApi(object : ApiInterface {})
        assertNotNull(RestSingletons.getApi())
    }
}