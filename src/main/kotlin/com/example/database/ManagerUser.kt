package com.example.database

import com.example.database.dbEntities.DBUserTable
import com.example.entities.user.User
import com.example.entities.user.UserDraft
import org.ktorm.dsl.*

class ManagerUser {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun getUserInfo(id: String): List<User> {
        return ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.oauthId eq id }
            .map{
                User(it[DBUserTable.id]!!, it[DBUserTable.name], it[DBUserTable.city], it[DBUserTable.street],
                    it[DBUserTable.apartmentNo], it[DBUserTable.postCode], it[DBUserTable.oauthId])
            }
    }

    fun updateUserInfo(userId : String, updatedUser: UserDraft): Boolean{
        val updatedRows = ktormDatabase.update(DBUserTable){
            set(DBUserTable.name, updatedUser.name)
            set(DBUserTable.city, updatedUser.city)
            set(DBUserTable.apartmentNo, updatedUser.apartmentNo)
            set(DBUserTable.postCode, updatedUser.postCode)
            set(DBUserTable.street, updatedUser.street)
            where {
                it.oauthId eq userId
            }
        }
        return updatedRows > 0
    }

    fun checkIfUserExists(oauthId: String):Boolean{
        val user = ktormDatabase.from(DBUserTable).select()
            .where{
                DBUserTable.oauthId eq oauthId
            }
            .map{
                User(it[DBUserTable.id]!!, it[DBUserTable.name], null, null,
                    null, null, it[DBUserTable.oauthId])
            }
        return user.size !== 0
    }
    fun createUser(id: String, name: String?): Int? {
        var userExists = checkIfUserExists(id)
        if (userExists) {
            var userId = ktormDatabase.from(DBUserTable).select(DBUserTable.id)
                .where {
                    DBUserTable.oauthId eq id
                }
                .map {
                    User(
                        it[DBUserTable.id]!!, it[DBUserTable.name], null, null,
                        null, null, it[DBUserTable.oauthId]
                    )
                }
            return userId[0].id
        } else {
            var userId = ktormDatabase.insertAndGenerateKey(DBUserTable) {
                set(DBUserTable.name, name)
                set(DBUserTable.oauthId, id)
            } as Int
            return userId
        }
        return null
  }
}
