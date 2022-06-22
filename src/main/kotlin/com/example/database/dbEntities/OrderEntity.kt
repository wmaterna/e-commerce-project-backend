package com.example.database.dbEntities

import com.example.database.dbEntities.DBProductTable.references
import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBOrderTable: Table<DBOrderEntity>("order") {

    val id = int("id").primaryKey().bindTo { it.id }
    val user = int("user_id").references(DBUserTable) { it.user }
    val date = varchar("date").bindTo{ it.date }
    val address = varchar("address").bindTo{ it.address}
    val price = double("price").bindTo { it.price }

}

interface DBOrderEntity: Entity<DBOrderEntity> {
    companion object: Entity.Factory<DBOrderEntity>()
    val id: Int
    val user: DBUserEntity
    val date: String
    var address: String
    var price: Double
}