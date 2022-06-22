package com.example.database

import com.example.database.dbEntities.DBOpinionTable
import com.example.database.dbEntities.DBUserTable
import com.example.entities.opinion.Opinion
import com.example.entities.opinion.OpinionDraft
import com.example.entities.user.UserOpinionInfo
import org.ktorm.dsl.*

class ManagerOpinion {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun addOpinion(newOpinion: OpinionDraft): Opinion {
        val insertedId = ktormDatabase.insertAndGenerateKey(DBOpinionTable) {
            set(DBOpinionTable.user, newOpinion.user)
            set(DBOpinionTable.product_id, newOpinion.product)
            set(DBOpinionTable.content, newOpinion.content)
        } as Int

        val user = ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.id eq newOpinion.user}
            .map {
                UserOpinionInfo(newOpinion.user, it[DBUserTable.name]!!)
            }

        return Opinion(insertedId, user, newOpinion.product, newOpinion.content)
    }

    fun removeOpinion(id: Int): Boolean{
        val deleteRow = ktormDatabase.delete(DBOpinionTable){
            it.id eq id
        }
        return deleteRow > 0
    }
}