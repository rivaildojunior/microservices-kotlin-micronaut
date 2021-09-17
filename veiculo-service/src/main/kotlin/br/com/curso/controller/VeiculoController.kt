package br.com.curso.controller

import br.com.curso.model.Veiculo
import br.com.curso.service.VeiculoService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import java.net.URI

@Controller("/veiculos")
class VeiculoController(val veiculoService: VeiculoService) {

    @Post
    fun save(@Body veiculo: Veiculo): HttpResponse<Veiculo> {
        val veiculoSalvo: Veiculo = veiculoService.save(veiculo)
        val uri: URI = UriBuilder.of("/veiculos/${veiculoSalvo.id}").build()
        return HttpResponse.created(veiculoSalvo, uri)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): Veiculo {
        return veiculoService.findById(id)
    }

    @Delete("/{id}")
    fun deleteById(@PathVariable id: Long): HttpResponse<Unit> {
        veiculoService.deleteById(id)
        return HttpResponse.noContent();
    }

    @Get
    fun findAll(): List<Veiculo> {
        return veiculoService.findAll()
    }

    @Put("/{id}")
    fun update(@Body veiculo: Veiculo, @PathVariable id: Long): HttpResponse<Veiculo> {
        return HttpResponse.ok(veiculoService.update(veiculo, id))
    }

}