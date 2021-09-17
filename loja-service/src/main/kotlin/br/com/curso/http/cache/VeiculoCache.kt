package br.com.curso.http.cache

import br.com.curso.http.ClienteHttp
import br.com.curso.http.VeiculoHttp
import br.com.curso.model.Cliente
import br.com.curso.model.Veiculo
import com.fasterxml.jackson.databind.ObjectMapper
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VeiculoCache(
        val veiculoHttp: VeiculoHttp,
        var obj: ObjectMapper
) {

    fun getVeiculo(id: Long): Veiculo {
        val veiculo: Veiculo = veiculoHttp.getVeiculo(id)
        gravarCache(veiculo)
        return veiculo
    }

    fun gravarCache(veiculo: Veiculo) {
        val jedisPool: JedisPool = JedisPool(JedisPoolConfig(), "localhost", 6379)
        var jedis: Jedis = jedisPool.resource;
        var veiculoStr = obj.writeValueAsString(veiculo)
        jedis.set(veiculo.id.toString().plus("v"), veiculoStr)
    }
}