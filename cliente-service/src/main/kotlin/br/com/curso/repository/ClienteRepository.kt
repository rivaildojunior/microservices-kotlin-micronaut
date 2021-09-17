package br.com.curso.repository

import br.com.curso.model.Cliente
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.annotation.QueryValue
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
abstract class AbstractClienteRepository(
        private val entityManager: EntityManager
) : JpaRepository<Cliente, Long> {


    abstract fun findByNome(nome: String?, paginacao: Pageable): Page<Cliente>

    @Transactional
    fun listar(nome: String?, endereco: String?): List<Cliente> {
        var queryStr = "select c from br.com.curso.model.Cliente c where 1=1 "
        if (nome != null) {
            queryStr += " and nome = :nome"
        }
        if (endereco != null) {
            queryStr += " and endereco = :endereco"
        }

        var query = entityManager.createQuery(queryStr)

        if (nome != null) {
            query.setParameter("nome", nome)
        }
        if (endereco != null) {
            query.setParameter("endereco", endereco)
        }

        var clientes = query.resultList
        return clientes as List<Cliente>
    }
}