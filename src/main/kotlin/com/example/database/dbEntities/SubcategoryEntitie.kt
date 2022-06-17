package com.example.database.dbEntities

import com.example.entities.product.Product
import io.ktor.server.html.*
import org.ktorm.entity.Entity
import org.ktorm.*
import org.ktorm.schema.*

object DBSubcategoryTable: Table<DBSubcategorieEntity>("subcategory") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
}


interface DBSubcategorieEntity: Entity<DBSubcategorieEntity> {
    companion object: Entity.Factory<DBSubcategorieEntity>()
    val id: Int
    val name: String
    val products get() = listOf(DBProductEntity)
//    val productId: DBProductEntity
//    val products: List<DBProductEntity>
}