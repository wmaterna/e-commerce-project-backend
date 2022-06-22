package com.example.database.dbEntities

import com.example.database.dbEntities.DBSubcategoryTable.bindTo
import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBOrderDetailsTable: Table<DBOrderDetailsEntity>("order_detail") {

    val id = int("id").primaryKey().bindTo { it.id }
    val order = int("order_id").references(DBOrderTable) { it.order }
    val product = int("product_id").references(DBProductTable) { it.product }
    val quantity = int("quantity").bindTo { it.quantity }

}

interface DBOrderDetailsEntity: Entity<DBOrderDetailsEntity> {
    companion object: Entity.Factory<DBOrderEntity>()
    val id: Int
    val order: DBOrderEntity
    val product: DBProductEntity
    val quantity: Int
}