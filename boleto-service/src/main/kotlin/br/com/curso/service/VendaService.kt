package br.com.curso.service

import br.com.curso.model.Venda
import br.com.curso.repository.VendaRepository
import javax.inject.Singleton

@Singleton
class VendaService(
       val vendaRepository: VendaRepository
        ) {

    fun gravar(venda: Venda) {
        vendaRepository.create(venda)
    }
}