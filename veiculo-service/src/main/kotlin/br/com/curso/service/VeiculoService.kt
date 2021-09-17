package br.com.curso.service

import br.com.curso.model.Veiculo
import br.com.curso.repository.VeiculoRepository
import br.com.curso.service.exception.RegistroNaoEncontradoException
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import javax.inject.Singleton

@Singleton
open class VeiculoService (val veiculoRepository: VeiculoRepository){

    open fun save(veiculo: Veiculo): Veiculo {
        veiculoRepository.save(veiculo)
        return veiculo
    }

    open fun findById(id: Long): Veiculo {
        return veiculoRepository.findById(id)
                .orElseThrow{RegistroNaoEncontradoException("Registro não encontrado")}
    }

    open fun deleteById(id: Long): Unit {
        var veiculoDB: Veiculo = findById(id);
        return veiculoRepository.delete(veiculoDB)
    }

    open fun findAll(): List<Veiculo> {
        return veiculoRepository.findAll()
    }

    open fun update(veiculo: Veiculo, id: Long): Veiculo {
        var veiculoDB: Veiculo = findById(id);
        //veiculoDB.nome = veiculo.nome;
        return veiculoRepository.update(veiculoDB)
    }

}