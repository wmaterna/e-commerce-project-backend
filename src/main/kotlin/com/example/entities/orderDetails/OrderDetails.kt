package com.example.entities.orderDetails

import com.example.entities.product.Product

data class OrderDetails(
    val id: Int,
    var order: Int,
    var quantity: Int,
    var product: List<Product>,

)
