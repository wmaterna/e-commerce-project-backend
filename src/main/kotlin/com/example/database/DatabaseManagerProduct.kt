package com.example.database

import com.example.database.dbEntities.DBProductTable
import com.example.entities.product.Product
import org.ktorm.dsl.*
import org.ktorm.entity.toList

class DatabaseManagerProduct {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

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

    fun getProduct(id: Int): List<Product> {
        return ktormDatabase.from(DBProductTable)
            .select()
            .where{ DBProductTable.id eq id}
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

}