package com.example.entities.order

import com.example.entities.orderDetails.OrderDetails
import com.example.entities.user.User

data class Order(
    val id: Int,
    var date: String,
    var address: String,
    var price: Double,
    var user: User,
    var products: List<OrderDetails>,
)
