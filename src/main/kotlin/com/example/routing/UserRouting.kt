package com.example.routing

import com.example.database.ManagerUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {

    val userRepo = ManagerUser()

    get("/user/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest, "Wrong id"
            )
            return@get
        } else {
            val user = userRepo.getUserInfo(id)
            if (user == null) {
                call.respond(
                    HttpStatusCode.NotFound, "Not found user"
                )
            } else {
                call.respond(user)
            }
        }
    }
}