package com.admc.todosapp.data.database.entities

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.admc.todosapp.domain.model.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "email") val email:String,
    @ColumnInfo(name = "password") val password:String
)

