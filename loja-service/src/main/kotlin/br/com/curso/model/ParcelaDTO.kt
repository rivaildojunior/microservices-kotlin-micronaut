package br.com.curso.model

import java.math.BigDecimal

data class ParcelaDTO(
        var valor: BigDecimal,
        var dataVencimento: String
)
