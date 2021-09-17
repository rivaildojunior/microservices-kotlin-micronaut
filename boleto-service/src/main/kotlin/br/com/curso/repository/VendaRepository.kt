package br.com.curso.repository

import br.com.curso.model.Venda
import com.mongodb.client.MongoClient
import com.mongodb.client.result.InsertOneResult
import javax.inject.Singleton


@Singleton
class VendaRepository(
        private val mongoClient: MongoClient
) {

    fun create(venda: Venda): InsertOneResult {
        return getCollection().insertOne(venda)
    }

    private fun getCollection() =
            mongoClient.getDatabase("exemplo")
                    .getCollection("venda", Venda::class.java)
}