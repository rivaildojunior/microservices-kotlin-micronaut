package br.com.curso.producer

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface VendaProducer {

    @Topic("ms-vendas")
    fun enviarVenda(@KafkaKey id: String, body: String)
}