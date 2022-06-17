package com.example.entities.product

import com.example.database.dbEntities.DBSubcategorieEntity
import com.example.entities.opinion.Opinion
import com.example.entities.subcategory.Subcategory

data class Product(
    val id: Int,
    var name: String,
    var description: String,
    var price: Long,
    var recommendations: String,
    var subcategoryId: DBSubcategorieEntity
//    var opinions: List<Opinion>
)
