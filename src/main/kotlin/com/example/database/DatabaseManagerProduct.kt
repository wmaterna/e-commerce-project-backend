package com.example.database

import com.example.database.dbEntities.DBProductEntity
import com.example.database.dbEntities.DBProductTable
import org.ktorm.dsl.eq
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManagerProduct {
    private val database = DatabaseManager()
    private val databaseConnection = database.dataBaseConnection()

    fun getAllProducts(): List<DBProductEntity> {
        return databaseConnection.sequenceOf(DBProductTable).toList()
    }

    fun getProduct(id: Int): DBProductEntity? {
        return databaseConnection.sequenceOf(DBProductTable)
            .firstOrNull { it.id eq id }
    }

}