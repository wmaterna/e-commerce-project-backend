package com.example.entities.subcategory

import com.example.database.dbEntities.DBProductEntity
import com.example.entities.product.Product

data class Subcategory(
    val id: Int,
    var name: String,
    var products: List<Product>
)
