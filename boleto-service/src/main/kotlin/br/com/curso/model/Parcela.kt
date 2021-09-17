package br.com.curso.model

import org.bson.types.ObjectId
import java.math.BigDecimal

data class Parcela(
       // var id: ObjectId? = null,
        var valor: BigDecimal,
        var dataVencimento: String
)
