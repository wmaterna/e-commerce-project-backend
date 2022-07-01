package com.example.entities.orderDetails

import com.example.entities.product.ProuctInfo

data class NewOrder(
    var date: String,
    var price: Double,
    var address: String,
    var products: List<ProuctInfo>,
)
data class OrderDetailsResponse(
    var quantity: Int,
    var product: Int,
)
