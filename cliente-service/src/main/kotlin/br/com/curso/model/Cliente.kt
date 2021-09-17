package br.com.curso.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.core.annotation.Introspected
import javax.persistence.*


@Entity(name="cliente")
data class Cliente(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long? = null,
        @Column
        var nome: String? = null,
        @Column
        val endereco: String? = null
)