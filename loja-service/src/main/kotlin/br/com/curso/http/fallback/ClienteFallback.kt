package br.com.curso.http.fallback

import br.com.curso.http.ClienteHttp
import br.com.curso.model.Cliente
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class ClienteFallback(var obj: ObjectMapper) : ClienteHttp {

    override fun getCliente(id: Long): Cliente {
        println("passou no fallback")
        val jedisPool: JedisPool = JedisPool(JedisPoolConfig(), "localhost", 6379)
        var jedis: Jedis = jedisPool.resource;
        var value: String = jedis.get(id.toString().plus("c"));
        val cliente: Cliente = obj.readValue(value, Cliente::class.java)
        return cliente;
    }

}