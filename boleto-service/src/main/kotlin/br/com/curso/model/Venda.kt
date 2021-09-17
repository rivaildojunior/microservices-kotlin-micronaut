package br.com.curso.model

import org.bson.types.ObjectId
import java.math.BigDecimal


data class Venda(
        //var id: ObjectId? = null,
        val cliente: Cliente,
        val veiculo: Veiculo,
        val valor: BigDecimal,
        var parcelas: List<Parcela>
)