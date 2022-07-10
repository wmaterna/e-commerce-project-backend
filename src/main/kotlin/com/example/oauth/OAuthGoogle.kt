package com.example.oauth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.database.ManagerUser
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.gson.*
import kotlinx.serialization.Serializable
import java.util.*

val httC = HttpClient(CIO) {
    install(ContentNegotiation) {
        gson()
    }
}


fun Route.authenticationRoutes(httpClient: HttpClient = httC) {

    val secret = environment?.config?.property("jwt.secret")?.getString()
    val issuer = environment?.config?.property("jwt.issuer")?.getString()
    val audience = environment?.config?.property("jwt.audience")?.getString()

    val userRepo = ManagerUser()


    authenticate("auth-oauth-google") {
        get("/login-google") {
        }
        get("/hello") {
            val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
            if(principal?.accessToken.toString() != null){
                val userInfo: UserInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${principal?.accessToken.toString()}")
                    }
                }.body()

                userRepo.createUser(userInfo.id, userInfo.name);
                val token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withClaim("id", userInfo.id)
                    .withExpiresAt(Date(System.currentTimeMillis() + 1000000))
                    .sign(Algorithm.HMAC256(secret))

                call.respondRedirect("https://newappfront.azurewebsites.net/user/info?token=${token}")

            } else {
                call.respondRedirect("/")
            }
        }

    }
}

@Serializable
data class UserInfo(
    val id: String,
    val name: String,
)