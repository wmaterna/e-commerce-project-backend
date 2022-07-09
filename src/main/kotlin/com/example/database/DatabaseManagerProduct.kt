package com.example.database

import com.example.database.dbEntities.*
import com.example.entities.opinion.Opinion
import com.example.entities.product.Product
import com.example.entities.product.ProductDetails
import com.example.entities.user.UserOpinionInfo
import org.ktorm.dsl.*

class DatabaseManagerProduct {
    private val database = DatabaseManager()
    private val ktormDatabase = database.connect()

    fun getAllProducts(): List<Product> {
        return ktormDatabase.from(DBProductTable)
            .select()
            .map{
                Product(
                    it[DBProductTable.id]!!,
                    it[DBProductTable.name]!!,
                    it[DBProductTable.description]!!,
                    it[DBProductTable.price]!!,
                    it[DBProductTable.recommendations]!!,
                    it[DBProductTable.url]!!,
                    it[DBProductTable.subcategory]!!,
                )
            }

    }

    fun getProduct(id: Int): List<ProductDetails> {
        val opinios: List<Opinion> = ktormDatabase.from(DBOpinionTable)
            .select()
            .where{ DBOpinionTable.product_id eq id }
            .map{
                val user = ktormDatabase.from(DBUserTable)
                    .select()
                    .where { DBUserTable.id eq it[DBOpinionTable.user]!!}
                    .map{
                        UserOpinionInfo(it[DBUserTable.id]!!, it[DBUserTable.name]!!)
                    }
                Opinion(
                    it[DBOpinionTable.id]!!,
                    user,
                    it[DBOpinionTable.product_id]!!,
                    it[DBOpinionTable.content]!!
                )
            }
        val product = ktormDatabase.from(DBProductTable)
            .select()
            .where{ DBProductTable.id eq id}
            .map{
                ProductDetails(
                    it[DBProductTable.id]!!,
                    it[DBProductTable.name]!!,
                    it[DBProductTable.description]!!,
                    it[DBProductTable.price]!!,
                    it[DBProductTable.recommendations]!!,
                    it[DBProductTable.url]!!,
                    it[DBProductTable.subcategory]!!,
                    opinios
                )
            }
        return product
    }



}