package com.example.routing

import com.example.database.DatabaseManagerSubcategories
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.subcategoryRoutes(){

        val repository = DatabaseManagerSubcategories()

        get("/subcategory") {
            call.respond(repository.getAllSubcategories())
        }
        get("/subcategory/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if(id == null){
                call.respond(
                    HttpStatusCode.BadRequest,"Wrong id"
                )
                return@get
            }
            val subcategory = repository.getSubcategoryContent(id)
            if(subcategory == null){
                call.respond(
                    HttpStatusCode.NotFound, "Not found subcategories"
                )
            }else {
                call.respond(subcategory)
        }
     }
}
