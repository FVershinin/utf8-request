package com.example.utf8request

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@SpringBootApplication
class Application {

    @RequestMapping("/graphql", method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun request(@RequestParam query: String): String {
        return query
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}