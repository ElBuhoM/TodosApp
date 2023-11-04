package com.admc.todosapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.admc.todosapp.data.database.dao.TaskDao
import com.admc.todosapp.data.database.entities.TaskEntity
import com.admc.todosapp.data.database.entities.UserEntity

@Database(entities = [UserEntity::class, TaskEntity::class], version = 1)
abstract  class TaskDataBase:RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}