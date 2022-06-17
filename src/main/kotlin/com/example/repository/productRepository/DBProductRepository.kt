package com.example.repository.productRepository

import com.example.database.DatabaseManager
import com.example.database.DatabaseManagerProduct
import com.example.entities.product.Product

class DBProductRepository : ProductRepository {

    private val database = DatabaseManagerProduct()
//    override fun getAllProducts(): List<Product> {
//        return database.getAllProducts().map{ Product (it.id, it.name, it.description, it.price, it.recommendations, it.subcategory)}
//    }

//    override fun getProduct(id: Int): Product? {
//        return database.getProduct(id)
//            ?.let{ Product(it.id, it.name, it.description, it.price, it.recommendations, it.subcategory) }
//    }
}