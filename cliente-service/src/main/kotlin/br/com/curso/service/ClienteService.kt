package br.com.curso.service

import br.com.curso.model.Cliente
import br.com.curso.repository.AbstractClienteRepository
import br.com.curso.service.exception.RegistroNaoEncontradoException
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import javax.inject.Singleton

@Singleton
open class ClienteService(val clienteRepository: AbstractClienteRepository) {

    open fun save(cliente: Cliente): Cliente {
        clienteRepository.save(cliente)
        return cliente
    }

    open fun findById(id: Long): Cliente {
        return clienteRepository.findById(id)
                .orElseThrow{RegistroNaoEncontradoException("Registro não encontrado")}
    }

    open fun deleteById(id: Long): Unit {
        var clienteDB: Cliente = findById(id);
        return clienteRepository.delete(clienteDB)
    }

    open fun findAll(nome: String?, paginacao: Pageable): Page<Cliente> {
        var lista = if (nome == null){
            clienteRepository.findAll(paginacao)
        } else {
            clienteRepository.findByNome(nome, paginacao)
        }
        return lista
    }

    open fun findAll(nome: String?, endereco: String?): List<Cliente> {
        return clienteRepository.listar(nome, endereco)
    }

    open fun update(cliente: Cliente, id: Long): Cliente {
        var clienteDB: Cliente = findById(id);
        clienteDB.nome = cliente.nome;
        return clienteRepository.update(clienteDB)
    }
}