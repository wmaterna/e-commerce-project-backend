package com.example.entities.orderDetails

import com.example.entities.product.Product

data class OrderDetailsDraft(
    var quantity: Int,
    var product: Product
)
