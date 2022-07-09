package com.example.database

import com.example.database.dbEntities.DBOrderDetailsTable
import com.example.database.dbEntities.DBOrderTable
import com.example.database.dbEntities.DBProductTable
import com.example.database.dbEntities.DBUserTable
import com.example.entities.order.Order
import com.example.entities.order.OrderDraft
import com.example.entities.orderDetails.NewOrder
import com.example.entities.orderDetails.OrderDetails
import com.example.entities.product.Product
import com.example.entities.user.User
import org.ktorm.dsl.*

class ManagerOrder {
    private val database = DatabaseManager()
    private val ktormDatabase = database.connect()

    fun getUsersOrders(id: String): List<Order>{
        val user = ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.oauthId eq id }
            .map {
                User(
                    it[DBUserTable.id]!!,
                    it[DBUserTable.name],
                    it[DBUserTable.city],
                    it[DBUserTable.street],
                    it[DBUserTable.apartmentNo],
                    it[DBUserTable.postCode],
                    it[DBUserTable.oauthId]
                )
            }

        val order = ktormDatabase.from(DBOrderTable)
            .select()
            .where{ DBOrderTable.user eq user[0].id }
            .map{
                Order(
                    it[DBOrderTable.id]!!,
                    it[DBOrderTable.date]!!,
                    it[DBOrderTable.address]!!,
                    it[DBOrderTable.price]!!,
                    user[0],
                    ktormDatabase.from(DBOrderDetailsTable)
                        .select()
                        .where{ DBOrderDetailsTable.order eq it[DBOrderTable.id]!!}
                        .map{ OrderDetails(
                            it[DBOrderDetailsTable.id]!!,
                            it[DBOrderDetailsTable.order]!!,
                            it[DBOrderDetailsTable.quantity]!!,
                            ktormDatabase.from(DBProductTable)
                                .select()
                                .where { DBProductTable.id  eq it[DBOrderDetailsTable.product]!!}
                                .map{
                                    Product(
                                        it[DBProductTable.id]!!,
                                        it[DBProductTable.name]!!,
                                        it[DBProductTable.description]!!,
                                        it[DBProductTable.price]!!,
                                        it[DBProductTable.recommendations]!!,
                                        it[DBProductTable.url] !!,
                                        it[DBProductTable.subcategory]!!,
                                    )
                                }

                        )}
                )
            }
        return order
    }
    

    fun addOrder(newOrder: NewOrder, orderId: String): OrderDraft {
        val user = ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.oauthId eq orderId }
            .map {
                User(
                    it[DBUserTable.id]!!,
                    it[DBUserTable.name],
                    it[DBUserTable.city],
                    it[DBUserTable.street],
                    it[DBUserTable.apartmentNo],
                    it[DBUserTable.postCode],
                    it[DBUserTable.oauthId]
                )
            }

        val order = ktormDatabase.insertAndGenerateKey(DBOrderTable) {
             set(DBOrderTable.date, newOrder.date)
            set(DBOrderTable.user, user[0].id)
            set(DBOrderTable.address, newOrder.address)
            set(DBOrderTable.price, newOrder.price)
        } as Int

        return OrderDraft(order, newOrder.date, newOrder.address, newOrder.price)
    }
}