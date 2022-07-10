package com.example.oauth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.database.ManagerUser
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.authGithub(httpClient: HttpClient = httC) {

    val secret = environment?.config?.property("jwt.secret")?.getString()
    val issuer = environment?.config?.property("jwt.issuer")?.getString()
    val audience = environment?.config?.property("jwt.audience")?.getString()

    val userRepo = ManagerUser()

    authenticate("auth-oauth-github") {
        get("/login-github") {
        }

        get("/oauth-github") {
            val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
            if(principal?.accessToken.toString() != null){
                val userInfo: UserInfo = httpClient.get("https://api.github.com/user") {
                    header(key = HttpHeaders.Authorization, "token ${principal?.accessToken.toString()}")
                }.body()

                userRepo.createUser(userInfo.id, userInfo.name);
                val token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withClaim("id", userInfo.id)
                    .withExpiresAt(Date(System.currentTimeMillis() + 1000000))
                    .sign(Algorithm.HMAC256(secret))
                call.response.cookies.append(
                    Cookie(
                        "jwt-token",
                        token,
                    )
                )
                call.response.headers.append("Authorization", "Bearer $token")
                call.respondRedirect("https://newappfront.azurewebsites.net/user/info?token={$token}")
            } else {
                call.respondRedirect("/")
            }
        }
    }
}

