package br.com.curso.http

import br.com.curso.model.Cliente
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.CircuitBreaker

@Client(id = "cliente")
@CircuitBreaker(delay = "1s", attempts = "1", multiplier = "1", reset = "30s")
interface ClienteHttp {

    @Get("/clientes/{id}")
    fun getCliente(@PathVariable id: Long) : Cliente;
}