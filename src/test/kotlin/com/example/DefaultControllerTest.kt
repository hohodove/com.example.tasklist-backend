package com.example

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.example.infrastructure.framework.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class DefaultControllerTest {
    @Test
    fun `ルートパスにアクセスした場合、Hello World!がテキストで返却される`() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }

    data class HelloJson(val hello: String)

    @Test
    fun `HelloJsonパスにアクセスした場合、変数名：hello、値：worldのjsonが返却される`() {
        withTestApplication({
            configureRouting()
            configureSerialization()
        }) {
            handleRequest(HttpMethod.Get, "/helloJson").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val mapper = jacksonObjectMapper()
                val helloJson = mapper.readValue<HelloJson>(response.content!!)
                assertEquals("world", helloJson.hello)
            }
        }
    }


}
