package br.com.curso.model

import java.math.BigDecimal

data class Venda(
        val cliente: Long,
        val veiculo: Long,
        val valor: BigDecimal,
        val quantidadePacelas: Int
)