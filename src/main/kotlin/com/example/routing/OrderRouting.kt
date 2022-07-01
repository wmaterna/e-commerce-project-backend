package com.example.routing

import com.example.database.DatabaseManagerProduct
import com.example.database.ManagerOrder
import com.example.entities.order.OrderDraft
import com.example.entities.orderDetails.NewOrder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRouting(){

    val ordersRepo = ManagerOrder()

    post("/order"){
        val orderDraft = call.receive<NewOrder>()

        val principal = call.principal<JWTPrincipal>()
        principal?.getClaim("id", String::class)?.run {
            val order = ordersRepo.addOrder(orderDraft, this)
            if (order == null) {
                call.respond(
                    HttpStatusCode.NotFound, "Not found user"
                )
            } else {
                call.respond(order)
            }
        }
    }

    get("/order"){

        val principal = call.principal<JWTPrincipal>()
        principal?.getClaim("id", String::class)?.run {
            val orders = ordersRepo.getUsersOrders(this)
            if (orders == null) {
                call.respond(
                    HttpStatusCode.NotFound, "Not found user"
                )
            } else {
                call.respond(orders)
            }
        }
    }
//
//    get("/orders/{user_id}/{order_id}"){
//        val user_id = call.parameters["user_id"]?.toIntOrNull()
//        val order_id = call.parameters["order_id"]?.toIntOrNull()
//        if(user_id == null || order_id == null){
//            call.respond(
//                HttpStatusCode.BadRequest,"Wrong id"
//            )
//            return@get
//        }
//        val orders = ordersRepo.getOrderDetails(user_id, order_id)
//
//        if(orders == null){
//            call.respond(
//                HttpStatusCode.NotFound, "Not found orders"
//            )
//        } else {
//            call.respond(orders)
//        }
//
//    }
}