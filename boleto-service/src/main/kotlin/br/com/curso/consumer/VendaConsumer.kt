package br.com.curso.consumer


import br.com.curso.model.Venda
import br.com.curso.service.VendaService
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class VendaConsumer(var obj: ObjectMapper,
                    var service: VendaService) {

    @Topic("ms-vendas")
    fun receber(@KafkaKey id: String, body: String) {

        val venda: Venda = obj.readValue(body, Venda::class.java)

        service.gravar(venda)
        println("INSERIDO COM SUCESSO!!!")
    }
}