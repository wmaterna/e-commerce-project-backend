package com.example.entities.orderDetails

import com.example.entities.product.Product

data class NewOrderDetails(
    val id: Int,
    var order: Int,
    var quantity: Int,
    var product: Int,
)
data class OrderDetailsResponse(
    var quantity: Int,
    var product: Int,
)
