package com.example.routing

import com.example.database.ManagerCategory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.categoriesRouting(){

    val categoriesRepository = ManagerCategory()

    get("/categories"){
        call.respond(categoriesRepository.getCategories())
    }
    get("/categories/{id}"){
        val id = call.parameters["id"]?.toIntOrNull()
        if(id == null){
            call.respond(
                HttpStatusCode.BadRequest,"Wrong id"
            )
            return@get
        }

        val category = categoriesRepository.getCategory(id)
        if(category == null){
            call.respond(
                HttpStatusCode.NotFound, "Not found subcategories"
            )
        }else {
            call.respond(category)
        }
    }
}