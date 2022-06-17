package com.example.repository.productRepository

import com.example.database.DatabaseManagerSubcategories
import com.example.entities.subcategory.Subcategory

class DBSubcategoriesRepository : SubcategoryRepository {

    private val database = DatabaseManagerSubcategories()
//    private val databaseProduct = DatabaseManagerProduct()
    override fun getAllSubcategories(): List<Subcategory> {
//        var proucts = databaseProduct.getAllProducts()
//        database.getAllSubcategories().find{ proucts.map { it.id eq  }}
        return database.getAllSubcategories().map{ Subcategory(it.id, it.name, it.products) }

    }

//    override fun getSubcateogry(id: Int): Subcategory? {
//        TODO("Not yet implemented")
//    }
}