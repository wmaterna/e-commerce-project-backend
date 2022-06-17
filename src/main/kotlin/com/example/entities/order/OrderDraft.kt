package com.example.entities.order

import com.example.entities.orderDetails.OrderDetails
import com.example.entities.user.User

data class OrderDraft(
    var user: User,
    var products: List<OrderDetails>,
    var date: String,
    var address: String,
    var price: Long
)
