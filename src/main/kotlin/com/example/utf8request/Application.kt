package com.example.utf8request

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.router
import java.util.concurrent.CompletableFuture

@RestController
@SpringBootApplication
class Application {

    @GetMapping("/annotation-request/")
    fun request(): CompletableFuture<Int> = CompletableFuture.supplyAsync {
        Thread.sleep(1000)
        10
    }

    @Bean
    fun functionalEndpoint() = router {
        GET("/function-request/") {
            ok().body(CompletableFuture.supplyAsync {
                Thread.sleep(1000)
                10
            })
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}