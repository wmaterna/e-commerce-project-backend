package com.example.database

import com.example.database.dbEntities.DBSubcategorieEntity
import com.example.database.dbEntities.DBSubcategoryTable
import org.ktorm.database.Database
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.ktorm.dsl.eq
import org.ktorm.logging.Slf4jLoggerAdapter
import org.ktorm.support.sqlite.SQLiteDialect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DatabaseManagerSubcategories {
//    private val database = DatabaseManager()
//    private val databaseConnection = database.dataBaseConnection()


    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    private val ktormDatabase: Database

    init {
        ktormDatabase = Database.connect(
            url = "jdbc:sqlite:sample.db",
            logger = Slf4jLoggerAdapter(logger),
            dialect = SQLiteDialect()
        )
    }

    fun getAllSubcategories(): List<DBSubcategorieEntity> {
        return ktormDatabase.sequenceOf(DBSubcategoryTable).toList()
    }

//    fun getSubcategory(id: Int): DBSubcategorieEntity? {
//        return ktormDatabase.sequenceOf(DBSubcategoryTable).firstOrNull({ it.id eq id })
//    }



}