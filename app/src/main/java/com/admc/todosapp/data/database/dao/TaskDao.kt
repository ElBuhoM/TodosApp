package com.admc.todosapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.admc.todosapp.data.database.entities.TaskEntity
import com.admc.todosapp.data.database.entities.UserEntity
import com.admc.todosapp.domain.model.User

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE userId = :userId")
    suspend fun getTasksByUserId(userId: Long): List<TaskEntity>

    @Query("SELECT * FROM user_table WHERE email = :emailUser AND password = :passwordUser")
    suspend fun getUser(emailUser: String, passwordUser: String): List<UserEntity>

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}