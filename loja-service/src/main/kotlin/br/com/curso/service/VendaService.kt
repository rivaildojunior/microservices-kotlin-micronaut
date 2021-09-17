package br.com.curso.service

import br.com.curso.http.ClienteHttp
import br.com.curso.http.VeiculoHttp
import br.com.curso.http.cache.ClienteCache
import br.com.curso.http.cache.VeiculoCache
import br.com.curso.model.*
import br.com.curso.producer.VendaProducer
import com.fasterxml.jackson.databind.ObjectMapper
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
open class VendaService(
        val clienteCache: ClienteCache,
        val veiculoCache: VeiculoCache,
        val producer: VendaProducer
) {


    fun realizarVenda(venda: Venda): VendaDTO {

        var cliente: Cliente = clienteCache.getCliente(venda.cliente);
        var veiculo: Veiculo = veiculoCache.getVeiculo(venda.veiculo);
        var parcelas: List<ParcelaDTO> = ArrayList<ParcelaDTO>()
        var valorParcela: BigDecimal = venda.valor.divide(venda.quantidadePacelas.toBigDecimal())
        var dataVencimento = LocalDate.now().plusMonths(1);

        for (i in 1..venda.quantidadePacelas) {
            var parcela = ParcelaDTO(valorParcela, dataVencimento.toString())
            parcelas = parcelas.plus(parcela)
            dataVencimento = dataVencimento.plusMonths(1);
        }
        var vendaDTO: VendaDTO = VendaDTO(cliente, veiculo, venda.valor, parcelas);
        confirmarVenda(vendaDTO)
        return vendaDTO

    }

    fun confirmarVenda(venda: VendaDTO) {
        var obj: ObjectMapper = ObjectMapper()
        var vendaStr = obj.writeValueAsString(venda)
        producer.enviarVenda(UUID.randomUUID().toString(), vendaStr)
    }
}


