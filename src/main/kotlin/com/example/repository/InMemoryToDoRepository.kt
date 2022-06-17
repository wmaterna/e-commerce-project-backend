package com.example.repository

import com.example.entities.ToDo
import com.example.entities.ToDoDraft

class InMemoryToDoRepository : ToDoRepository {

    private val todo = mutableListOf<ToDo>(
        ToDo(1, "Plan API", false),
        ToDo(2, "Plan vacay", false)
    )
    override fun getAllToDos(): List<ToDo> {
        return todo
    }

    override fun getToDo(id: Int): ToDo? {
        return todo.firstOrNull{ it.id == id }
    }

    override fun addTodo(draft: ToDoDraft): ToDo {
        val todoElement = ToDo(
            id = todo.size + 1,
            title = draft.title,
            done = draft.done
        )
        todo.add(todoElement)
        return todoElement
    }

    override fun removeTodo(id: Int): Boolean {
        return todo.removeIf { it.id == id }
    }

    override fun updateTodo(id: Int, draft: ToDoDraft): Boolean {
        val todoElement = todo.firstOrNull{ it.id == id }
            ?: return false

        todoElement.title = draft.title
        todoElement.done = draft.done
        return true
    }

}