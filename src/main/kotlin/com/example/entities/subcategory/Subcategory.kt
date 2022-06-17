package com.example.entities.subcategory

import com.example.database.dbEntities.DBProductEntity

data class Subcategory(
    val id: Int,
    var name: String,
    var products: List<DBProductEntity>
)
