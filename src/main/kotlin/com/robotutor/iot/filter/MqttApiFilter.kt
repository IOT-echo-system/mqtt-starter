package com.robotutor.iot.filter

import com.robotutor.iot.services.MqttPublisher
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class MqttApiFilter(private val mqttPublisher: MqttPublisher) : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        return chain.filter(exchange)
            .contextWrite {
                it.put(MqttPublisher::class.java, mqttPublisher)
            }
    }
}
