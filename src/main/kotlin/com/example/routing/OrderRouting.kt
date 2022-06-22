package com.example.routing

import com.example.database.DatabaseManagerProduct
import com.example.database.ManagerOrder
import com.example.entities.order.OrderDraft
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRouting(){

    val ordersRepo = ManagerOrder()

    post("/order"){
        val orderDraft = call.receive<OrderDraft>()
        val orderId = ordersRepo.addOrder(orderDraft)
        call.respond(orderId)
    }

    get("/orders/{user_id}"){
        val id = call.parameters["user_id"]?.toIntOrNull()
        if(id == null){
            call.respond(
                HttpStatusCode.BadRequest,"Wrong id"
            )
            return@get
        }
        val orders = ordersRepo.getUsersOrders(id)
        if(orders == null){
            call.respond(
                HttpStatusCode.NotFound, "Not found orders"
            )
        } else {
            call.respond(orders)
        }
    }
    get("/orders/{user_id}/{order_id}"){
        val user_id = call.parameters["user_id"]?.toIntOrNull()
        val order_id = call.parameters["order_id"]?.toIntOrNull()
        if(user_id == null || order_id == null){
            call.respond(
                HttpStatusCode.BadRequest,"Wrong id"
            )
            return@get
        }
        val orders = ordersRepo.getOrderDetails(user_id, order_id)

        if(orders == null){
            call.respond(
                HttpStatusCode.NotFound, "Not found orders"
            )
        } else {
            call.respond(orders)
        }

    }
}