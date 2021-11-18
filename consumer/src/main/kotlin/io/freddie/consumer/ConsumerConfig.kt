package io.freddie.consumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class ConsumerConfig {
    @Bean
    fun receiveDomain1() = Consumer<Map<String, Any>> { message ->
        println("received domain1 message $message")
    }

    @Bean
    fun receiveDomain2() = Consumer<Map<String, Any>> { message ->
        println("received domain2 message $message")
    }

    @Bean
    fun receiveAllDomain() = Consumer<Map<String, Any>> { message ->
        println("received message $message")
    }
}