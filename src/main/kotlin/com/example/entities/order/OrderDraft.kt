package com.example.entities.order

import com.example.entities.orderDetails.OrderDetails
import com.example.entities.orderDetails.OrderDetailsResponse
import com.example.entities.user.User

data class OrderDraft(
    val id: Int,
    var date: String,
    var address: String,
    var price: Double,
)
