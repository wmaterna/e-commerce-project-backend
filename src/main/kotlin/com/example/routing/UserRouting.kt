package com.example.routing

import com.example.database.ManagerUser
import com.example.entities.user.UserDraft
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {

    val userRepo = ManagerUser()

    get("/user") {

        val principal = call.principal<JWTPrincipal>()
        principal?.getClaim("id", String::class)?.run {
            val user = userRepo.getUserInfo(this)
            if (user == null) {
                call.respond(
                    HttpStatusCode.NotFound, "Not found user"
                )
            } else {
                call.respond(user)
            }
        }
    }

    put("/user") {
        val userDraft = call.receive<UserDraft>()
        val principal = call.principal<JWTPrincipal>()
        principal?.getClaim("id", String::class)?.run {
            val updated = userRepo.updateUserInfo(this, userDraft)
            if (updated) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound,
                    "found no user with the id $this")
            }
        }
    }

}