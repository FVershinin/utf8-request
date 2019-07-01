package com.example.utf8request

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import java.net.URLEncoder

@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationTests {

    @Autowired
    private lateinit var context: ApplicationContext

    @Test
    fun testQuery() {
        val query = "query myQuery {foo}"
        val queryString = URLEncoder.encode(query, "UTF-8")
        val client = WebTestClient.bindToApplicationContext(context).build()
        client.get()
            .uri { uriBuilder -> uriBuilder.path("/graphql").queryParam("query", queryString).build() }
            .exchange()
            .expectBody<String>()
            .isEqualTo(query)
    }
}