package com.example.database

import com.example.database.dbEntities.DBOrderDetailsTable
import com.example.database.dbEntities.DBOrderTable
import com.example.database.dbEntities.DBProductTable
import com.example.database.dbEntities.DBUserTable
import com.example.entities.order.Order
import com.example.entities.order.OrderDraft
import com.example.entities.orderDetails.OrderDetails
import com.example.entities.product.Product
import com.example.entities.user.User
import kotlinx.html.ThScope
import org.ktorm.dsl.*

class ManagerOrder {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun getUsersOrders(id: Int): List<Order>{
       return ktormDatabase.from(DBOrderTable)
            .select()
            .where{ DBOrderTable.user eq id }
            .map{
                Order(
                    it[DBOrderTable.id]!!,
                    it[DBOrderTable.date]!!,
                    it[DBOrderTable.address]!!,
                    it[DBOrderTable.price]!!,
                ktormDatabase.from(DBUserTable)
                        .select()
                        .where{ DBUserTable.id eq id }
                        .map{
                            User(it[DBUserTable.id]!!, it[DBUserTable.name]!!, it[DBUserTable.city]!!, it[DBUserTable.street]!!,
                            it[DBUserTable.apartment_no]!!, it[DBUserTable.post_code]!!)
                        },
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

                    )})
            }
    }

    fun getOrderDetails(user_id: Int, order_id: Int): List<Order> {
        return ktormDatabase.from(DBOrderTable)
            .select()
            .where { (DBOrderTable.user eq user_id) and (DBOrderTable.id eq order_id) }
            .map {
                Order(
                    it[DBOrderTable.id]!!,
                    it[DBOrderTable.date]!!,
                    it[DBOrderTable.address]!!,
                    it[DBOrderTable.price]!!,
                    ktormDatabase.from(DBUserTable)
                        .select()
                        .where { DBUserTable.id eq user_id }
                        .map {
                            User(
                                it[DBUserTable.id]!!,
                                it[DBUserTable.name]!!,
                                it[DBUserTable.city]!!,
                                it[DBUserTable.street]!!,
                                it[DBUserTable.apartment_no]!!,
                                it[DBUserTable.post_code]!!
                            )
                        },
                    ktormDatabase.from(DBOrderDetailsTable)
                        .select()
                        .where { DBOrderDetailsTable.order eq it[DBOrderTable.id]!! }
                        .map {
                            OrderDetails(
                                it[DBOrderDetailsTable.id]!!,
                                it[DBOrderDetailsTable.order]!!,
                                it[DBOrderDetailsTable.quantity]!!,
                                ktormDatabase.from(DBProductTable)
                                    .select()
                                    .where { DBProductTable.id eq it[DBOrderDetailsTable.product]!! }
                                    .map {
                                        Product(
                                            it[DBProductTable.id]!!,
                                            it[DBProductTable.name]!!,
                                            it[DBProductTable.description]!!,
                                            it[DBProductTable.price]!!,
                                            it[DBProductTable.recommendations]!!,
                                            it[DBProductTable.url]!!,
                                            it[DBProductTable.subcategory]!!,
                                        )
                                    }

                            )
                        })
            }
    }

    fun addOrder(newOrder: OrderDraft): List<Any> {
        val user = ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.id eq newOrder.user }
            .map {
                User(
                    it[DBUserTable.id]!!,
                    it[DBUserTable.name]!!,
                    it[DBUserTable.city]!!,
                    it[DBUserTable.street]!!,
                    it[DBUserTable.apartment_no]!!,
                    it[DBUserTable.post_code]!!
                )
            }

        val orderId = ktormDatabase.insertAndGenerateKey(DBOrderTable) {
            set(DBOrderTable.user, user[0].id)
            set(DBOrderTable.date, newOrder.date)
            set(DBOrderTable.address, newOrder.address)
            set(DBOrderTable.price, newOrder.price)
        } as Int


        val createdOrderDetails = newOrder.products.map { row ->
            ktormDatabase.insertAndGenerateKey(DBOrderDetailsTable) {
                set(DBOrderDetailsTable.order, orderId)
                set(DBOrderDetailsTable.quantity, row.quantity)
                set(DBOrderDetailsTable.product,
                    ktormDatabase.from(DBProductTable)
                        .select()
                        .where { DBProductTable.id eq row.product }
                        .map {
                            Product(
                                it[DBProductTable.id]!!,
                                it[DBProductTable.name]!!,
                                it[DBProductTable.description]!!,
                                it[DBProductTable.price]!!,
                                it[DBProductTable.recommendations]!!,
                                it[DBProductTable.url]!!,
                                it[DBProductTable.subcategory]!!,
                            )
                        }[0].id)
            }
        }

        return createdOrderDetails
    }
}