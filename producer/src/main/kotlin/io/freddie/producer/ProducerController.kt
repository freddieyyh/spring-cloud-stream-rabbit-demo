package io.freddie.producer

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProducerController(
    private val streamBridge: StreamBridge
) {
    @PostMapping("/send")
    fun send(@RequestBody message: Message) {
        val bindingName = "system1-${message.event.value}-${message.domain.value}"
        streamBridge.send(
            bindingName,
            mapOf(
                "sender" to "producer1",
                "domain" to message.domain,
                "event" to message.event,
                "message" to message.message
            )
        )
    }

    data class Message(
        val event: Event,
        val domain: Domain,
        val message: Map<String, Any>
    )

    enum class Domain(val value: String) {
       DOMAIN1("domain1"),
       DOMAIN2 ("domain2")
    }

    enum class Event(val value: String) {
        CREATE("create"),
        UPDATE("update")
    }
}