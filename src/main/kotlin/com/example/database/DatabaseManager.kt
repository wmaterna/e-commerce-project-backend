package com.example.database

import org.ktorm.database.Database
import org.ktorm.logging.Slf4jLoggerAdapter
import org.ktorm.support.sqlite.SQLiteDialect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DatabaseManager {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    private val ktormDatabase: Database

    init {
        ktormDatabase = Database.connect(
            url = "jdbc:sqlite:sample.db",
            logger = Slf4jLoggerAdapter(logger),
            dialect = SQLiteDialect()
        )
    }

    fun dataBaseConnection(): Database{
        return ktormDatabase
    }
}