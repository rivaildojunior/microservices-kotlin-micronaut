package br.com.curso.http

import br.com.curso.model.Veiculo
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.CircuitBreaker

@Client(id = "http://localhost:8081")
@CircuitBreaker(delay = "1s", attempts = "1", multiplier = "1", reset = "30s")
interface VeiculoHttp {

    @Get("/veiculos/{id}")
    fun getVeiculo(@PathVariable id: Long) : Veiculo;
}