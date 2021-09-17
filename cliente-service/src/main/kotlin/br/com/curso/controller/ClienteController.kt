package br.com.curso.controller

import br.com.curso.model.Cliente
import br.com.curso.service.ClienteService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import java.net.URI

@Controller("/clientes")
class ClienteController(val clienteService: ClienteService) {

    @Post
    fun save(@Body cliente: Cliente): HttpResponse<Cliente> {
        val clienteSalvo: Cliente = clienteService.save(cliente)
        val uri: URI = UriBuilder.of("/clientes/${clienteSalvo.id}").build()
        return HttpResponse.created(clienteSalvo, uri)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): Cliente {
        return clienteService.findById(id)
    }

    @Delete("/{id}")
    fun deleteById(@PathVariable id: Long): HttpResponse<Unit> {
        clienteService.deleteById(id)
        return HttpResponse.noContent();
    }

    @Get
    fun findAll(@QueryValue nome: String?, paginacao: Pageable): Page<Cliente> {
        return clienteService.findAll(nome, paginacao)
    }
    @Get("/teste")
    fun findAll(@QueryValue nome: String?, @QueryValue endereco: String?): List<Cliente> {
        return clienteService.findAll(nome, endereco)
    }

    @Put("/{id}")
    fun update(@Body cliente: Cliente, @PathVariable id: Long): HttpResponse<Cliente> {
        return HttpResponse.ok(clienteService.update(cliente, id))
    }
}