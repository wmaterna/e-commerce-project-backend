package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.database.DatabaseManagerProduct
import com.example.database.DatabaseManagerSubcategories
import com.example.oauth.authGithub
import com.example.oauth.authenticationRoutes
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.serialization.gson.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import com.example.repository.productRepository.DBSubcategoriesRepository
import com.example.repository.productRepository.ProductRepository
import com.example.repository.productRepository.SubcategoryRepository

data class User(val id: String, val name: String)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {

    val secret = System.getenv("KTOR_JWT_SECRET")
    val issuer =  System.getenv("KTOR_JWT_ISSUER")
    val audience =  System.getenv("KTOR_JWT_AUDIENCE")
    val myRealm =  System.getenv("KTOR_JWT_REALM")

    install(Authentication) {

        oauth("auth-oauth-google") {
            urlProvider = { "http://localhost:8080/hello" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("GOOGLE_CLIENT_ID"),
                    clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile")
                )
            }
            client = HttpClient(CIO)
        }
        oauth("auth-oauth-github") {
            urlProvider = { "http://localhost:8080/oauth-github" }
            client = HttpClient(CIO)
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "github",
                    authorizeUrl = "https://github.com/login/oauth/authorize",
                    accessTokenUrl = "https://github.com/login/oauth/access_token",
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("GITHUB_CLIENT_ID"),
                    clientSecret = System.getenv("GITHUB_CLIENT_SECRET")
                )
            }
        }

        jwt("auth-jwt") {
            realm = myRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build())

            validate { credential ->
                if (credential.payload.getClaim("name").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }

    }
    install(CORS) {
        allowHost("*")
        allowHeader(HttpHeaders.ContentType)
    }

    install(ContentNegotiation) {
        gson()
        json()
    }
    install(Routing) {

//        val repository: SubcategoryRepository = DBSubcategoriesRepository()
        val repository = DatabaseManagerSubcategories()
        val productRepo = DatabaseManagerProduct()


        get("/") {
            call.respondText("Hello World!")
        }
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
        get("/products"){
            call.respond(productRepo.getAllProducts())
        }
        get("/products/{id}"){
        val id = call.parameters["id"]?.toIntOrNull()
        if(id == null){
            call.respond(
                HttpStatusCode.BadRequest,"Wrong id"
            )
            return@get
        }
        val product = productRepo.getProduct(id)
        if(product == null){
            call.respond(
                HttpStatusCode.NotFound, "Not found subcategories"
            )
        }else {
            call.respond(product)
        }
    }

        authenticationRoutes()
        authGithub()
    }
}



