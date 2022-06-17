package com.example.database

import com.example.database.dbEntities.DBProductTable
import com.example.database.dbEntities.DBSubcategoryTable
import com.example.entities.product.Product
import com.example.entities.subcategory.Subcategory
import org.ktorm.dsl.*

class DatabaseManagerSubcategories {

    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun getAllSubcategories(): List<Subcategory> {
        return ktormDatabase.from(DBSubcategoryTable)
            .select()
            .map {
                Subcategory(
                    it[DBSubcategoryTable.id]!!,
                    it[DBSubcategoryTable.name]!!,
                    ktormDatabase.from(DBProductTable)
                        .select()
                        .where { DBProductTable.subcategory eq it[DBSubcategoryTable.id]!! }
                        .map {
                            Product(
                                it[DBProductTable.id]!!,
                                it[DBProductTable.name]!!,
                                it[DBProductTable.description]!!,
                                it[DBProductTable.price]!!,
                                it[DBProductTable.recommendations]!!,
                                it[DBProductTable.url] !!,
                                it[DBProductTable.subcategory]!!
                            )
                        }
                )
            }
    }

    fun getSubcategoryContent(id: Int): List<Subcategory> {
        val products: List<Product>
        products = ktormDatabase.from(DBProductTable)
            .select()
            .where { DBProductTable.subcategory eq id!! }
            .map {
                Product(
                    it[DBProductTable.id]!!,
                    it[DBProductTable.name]!!,
                    it[DBProductTable.description]!!,
                    it[DBProductTable.price]!!,
                    it[DBProductTable.recommendations]!!,
                    it[DBProductTable.url] !!,
                    it[DBProductTable.subcategory]!!
                )
            }

        val sub = ktormDatabase.from(DBSubcategoryTable)
            .select()
            .where { DBSubcategoryTable.id eq id }
            .map{ row -> Subcategory(row[DBSubcategoryTable.id]!!, row[DBSubcategoryTable.name]!!, products)}

        return sub

  }


}
