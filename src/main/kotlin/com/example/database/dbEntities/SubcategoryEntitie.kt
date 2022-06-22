package com.example.database.dbEntities

import com.example.entities.product.Product
import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBSubcategoryTable: Table<DBSubcategorieEntity>("subcategory") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val category = int("category_id").references(DBCategoryTable) { it.category }
}


interface DBSubcategorieEntity: Entity<DBSubcategorieEntity> {
    companion object: Entity.Factory<DBSubcategorieEntity>()
    val id: Int
    val name: String
    val products get() = listOf(DBProductEntity)
    val category: DBCategoryEntity
//    val products get() = DBProductTable.findList { it.subcategory eq id }
//    val productId: DBProductEntity
//    val products: List<DBProductEntity>
}