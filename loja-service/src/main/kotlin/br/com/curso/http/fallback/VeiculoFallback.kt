package br.com.curso.http.fallback

import br.com.curso.http.VeiculoHttp
import br.com.curso.model.Veiculo
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class VeiculoFallback(var obj: ObjectMapper): VeiculoHttp {

    override fun getVeiculo(id: Long): Veiculo {
        val jedisPool: JedisPool = JedisPool(JedisPoolConfig(), "localhost", 6379)
        var jedis: Jedis = jedisPool.resource;
        var value: String = jedis.get(id.toString().plus("v"));
        val veiculo: Veiculo = obj.readValue(value, Veiculo::class.java)
        return veiculo;
    }
}