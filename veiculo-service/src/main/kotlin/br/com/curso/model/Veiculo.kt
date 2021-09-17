package br.com.curso.model

import javax.persistence.*

@Entity(name="veiculo")
data class Veiculo (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long? = null,
        @Column
        val marca: String? = null,
        @Column
        val modelo: String? = null,
        @Column
        val placa: String? = null
        )