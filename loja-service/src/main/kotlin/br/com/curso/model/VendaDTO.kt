package br.com.curso.model

import java.math.BigDecimal

data class VendaDTO(
        val cliente: Cliente,
        val veiculo: Veiculo,
        val valor: BigDecimal,
        var parcelas: List<ParcelaDTO>
)