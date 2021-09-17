package br.com.curso.http.cache

import br.com.curso.http.ClienteHttp
import br.com.curso.model.Cliente
import com.fasterxml.jackson.databind.ObjectMapper
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClienteCache(
        val clienteHttp: ClienteHttp,
        var obj: ObjectMapper
) {

    fun getCliente(id: Long): Cliente {
        val cliente: Cliente = clienteHttp.getCliente(id)
        gravarCache(cliente)
        return cliente
    }

    fun gravarCache(cliente: Cliente) {
        val jedisPool: JedisPool = JedisPool(JedisPoolConfig(), "localhost", 6379)
        var jedis: Jedis = jedisPool.resource;
        var clienteStr = obj.writeValueAsString(cliente)
        jedis.set(cliente.id.toString().plus("c"), clienteStr)
    }
}