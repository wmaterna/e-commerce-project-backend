package com.example.routing

//import com.example.repository.OpinionRepository
import io.ktor.resources.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/opinion")
class Opinion {
    fun Route.opinionRoures(){

    }
}