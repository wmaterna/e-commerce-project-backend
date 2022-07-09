package com.example.database

import org.ktorm.database.Database
import org.ktorm.logging.Slf4jLoggerAdapter
import org.ktorm.support.sqlite.SQLiteDialect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DatabaseManager {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    fun connect(): Database {
        // in memory database
        // url = "jdbc:sqlite::memory:"

        var ktormDatabase: Database = Database.connect(
            url = "jdbc:sqlite:sample.db",
            logger = Slf4jLoggerAdapter(logger),
            dialect = SQLiteDialect()
        )
        return ktormDatabase
    }

    fun execSqlScript(filename: String, database: Database) {
        database.useConnection { conn ->
            conn.createStatement().use { statement ->
                javaClass.classLoader
                    ?.getResourceAsStream(filename)
                    ?.bufferedReader()
                    ?.use { reader ->
                        for (sql in reader.readText().split(';')) {
                            if (sql.any { it.isLetterOrDigit() }) {
                                statement.executeUpdate(sql)
                            }
                        }
                    }
            }
        }
    }
//    init {
//        ktormDatabase = Database.connect(
//            url = "jdbc:sqlite:sample.db",
//            logger = Slf4jLoggerAdapter(logger),
//            dialect = SQLiteDialect()
//        )
//    }

//    fun dataBaseConnection(): Database{
//        return ktormDatabase
//    }
}