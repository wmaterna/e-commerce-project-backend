package com.example.routing

import com.example.database.ManagerOpinion
import com.example.entities.opinion.OpinionDraft
import io.ktor.server.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.opinionRouting(){

    val opinionRepo = ManagerOpinion()

    post("/opinion"){
        val opinionDraft = call.receive<OpinionDraft>()
        val opinionId = opinionRepo.addOpinion(opinionDraft)
        call.respond(opinionId)
    }


    delete("/opinion/{id}"){
        val opinion = call.parameters["id"]?.toIntOrNull()

        if (opinion == null) {
            call.respond(HttpStatusCode.BadRequest,
                "id parameter has to be a number!")
            return@delete
        }
        val removed = opinionRepo.removeOpinion(opinion)
        if(removed){
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound,
                "found no opinion with the id $opinion")
        }
    }
}