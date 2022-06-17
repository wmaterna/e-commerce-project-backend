package com.example.database

import com.example.entities.ToDo
import com.example.entities.ToDoDraft
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.firstOrNull
import org.ktorm.logging.Slf4jLoggerAdapter
import org.ktorm.support.sqlite.SQLiteDialect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

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

    fun getAllTodos(): List<DBTodoEntity> {
      return ktormDatabase.sequenceOf(DBTodoTable).toList()
    }
//
//    fun getTodo(id: Int): DBTodoEntity? {
//        return ktormDatabase.sequenceOf(DBTodoTable)
//            .firstOrNull { it.id eq id }
//    }
//
//    fun addTodo(draft: ToDoDraft): ToDo {
//        val insertedId = ktormDatabase.insertAndGenerateKey(DBTodoTable) {
//            set(DBTodoTable.title, draft.title)
//            set(DBTodoTable.done, draft.done)
//        } as Int
//
//        return ToDo(insertedId, draft.title, draft.done)
//    }
//
//    fun updateTodo(id: Int, draft: ToDoDraft): Boolean {
//        val updatedRows = ktormDatabase.update(DBTodoTable) {
//            set(DBTodoTable.title, draft.title)
//            set(DBTodoTable.done, draft.done)
//            where {
//                it.id eq id
//            }
//        }
//
//        return updatedRows > 0
//    }
//
//    fun removeTodo(id: Int): Boolean {
//        val deletedRows = ktormDatabase.delete(DBTodoTable) {
//            it.id eq id
//        }
//        return deletedRows > 0
//    }

}