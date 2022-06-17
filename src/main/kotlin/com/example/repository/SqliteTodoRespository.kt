package com.example.repository

import com.example.database.DatabaseManager
import com.example.entities.ToDo
import com.example.entities.ToDoDraft

//class SqliteTodoRespository : ToDoRepository {
//
////    private val database = DatabaseManager()
////
////
////    override fun getAllToDos(): List<ToDo> {
////        return database.getAllTodos()
////            .map{ ToDo(it.id, it.title, it.done) }
////    }
////
////    override fun getToDo(id: Int): ToDo? {
////        return database.getTodo(id)
////            ?.let { ToDo(it.id, it.title, it.done) }
////    }
////
////    override fun addTodo(draft: ToDoDraft): ToDo {
////        return database.addTodo(draft)
////    }
////
////    override fun removeTodo(id: Int): Boolean {
////        return database.removeTodo(id)
////    }
////
////    override fun updateTodo(id: Int, draft: ToDoDraft): Boolean {
////        return database.updateTodo(id, draft)
////    }
//}