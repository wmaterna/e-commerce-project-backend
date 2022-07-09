package com.example


import com.stripe.Stripe
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.database.DatabaseManager
import com.example.entities.payment.Payments
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
import com.example.routing.*
import io.ktor.server.request.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    val INITSCRIPT = "init-sqlite-data.sql"
    val sqliteDatabase = DatabaseManager()
    val db = sqliteDatabase.connect()

    sqliteDatabase.execSqlScript(INITSCRIPT, db)
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val myRealm = environment.config.property("jwt.realm").getString()

    install(Authentication) {

        oauth("auth-oauth-google") {
            urlProvider = { "https://backendplant.azurewebsites.net/hello" }
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
            urlProvider = { "https://backendplant.azurewebsites.net/oauth-github" }
            client = HttpClient(CIO)
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "github",
                    authorizeUrl = "https://github.com/login/oauth/authorize",
                    accessTokenUrl = "https://github.com/login/oauth/access_token",
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("GITHUB_CLIENT_ID"),
                    clientSecret = System.getenv("GITHUB_CLIENT_SECRET"),
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile")
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
        anyHost()
//        allowHost("newappfront.azurewebsites:3000")
//        allowHost("newappfront.azurewebsites")
//        allowHost("newappfront.azurewebsites", subDomains = listOf("en", "de", "es","net"))
//        allowHost("newappfront.azurewebsites", schemes = listOf("http", "https"))
//        allowHeader(HttpHeaders.ContentType)
//        allowMethod(HttpMethod.Put)
//        allowMethod(HttpMethod.Delete)
//        allowHeader(HttpHeaders.Authorization)
    }

    install(ContentNegotiation) {
        gson()
        json()
    }
    install(Routing) {
        subcategoryRoutes()
        productsRouting()
        categoriesRouting()


        get("/") {
            call.respondText("Hello World!")

        }
        Stripe.apiKey = System.getenv("STRIPE_API_KEY")

        post("/createPayment") {
            val paymentsAmount = call.receive<Payments>()

            val paymentParams: PaymentIntentCreateParams = PaymentIntentCreateParams.builder()
                .setAmount((paymentsAmount.amount * 100).toLong())
                .setCurrency("pln")
                .setAutomaticPaymentMethods(
                    PaymentIntentCreateParams.AutomaticPaymentMethods
                        .builder()
                        .setEnabled(true)
                        .build()
                )
                .build()

            val payment: PaymentIntent = PaymentIntent.create(paymentParams)
            call.respond(object { val clientSecret = payment.clientSecret })
        }


        authenticationRoutes()
        authGithub()
        authenticate("auth-jwt") {
            opinionRouting()
            orderRouting()
            userRouting()
        }
    }
}



