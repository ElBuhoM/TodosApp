package com.admc.todosapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.admc.todosapp.domain.model.Task

@Entity(tableName = "task_table"  ,foreignKeys = [ForeignKey(
    entity = UserEntity::class,
    parentColumns = ["id"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE // Opcional: Define la acción en cascada
)])
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "userId") val userId: Long = 0,
    @ColumnInfo(name = "taskName") val taskName:String ="",
    @ColumnInfo(name = "date") val date:String = ""
)
