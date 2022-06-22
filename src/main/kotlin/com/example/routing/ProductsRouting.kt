package com.example.routing

import com.example.database.DatabaseManagerProduct
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.productsRouting(){

    val productRepository = DatabaseManagerProduct()

    get("/products"){
        call.respond(productRepository.getAllProducts())
    }
    get("/products/{id}"){
        val id = call.parameters["id"]?.toIntOrNull()
        if(id == null){
            call.respond(
                HttpStatusCode.BadRequest,"Wrong id"
            )
            return@get
        }

        val product = productRepository.getProduct(id)
        if(product == null){
            call.respond(
                HttpStatusCode.NotFound, "Not found subcategories"
            )
        }else {
            call.respond(product)
        }
    }
}