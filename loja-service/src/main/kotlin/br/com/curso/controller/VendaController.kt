package br.com.curso.controller

import br.com.curso.model.Venda
import br.com.curso.model.VendaDTO
import br.com.curso.service.VendaService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.HttpResponse

@Controller("/vendas")
class VendaController(val vendaService: VendaService) {


    @Post
    fun realizarVenda(@Body venda: Venda): HttpResponse<VendaDTO> {
        val vendaDTO = vendaService.realizarVenda(venda)
        return HttpResponse.created(vendaDTO)
    }
}