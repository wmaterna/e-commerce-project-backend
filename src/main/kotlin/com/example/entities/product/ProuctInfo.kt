package com.example.entities.product

import com.stripe.param.PaymentLinkCreateParams.LineItem.AdjustableQuantity

data class ProuctInfo(
    val product: Product,
    val quantity: Int
)
