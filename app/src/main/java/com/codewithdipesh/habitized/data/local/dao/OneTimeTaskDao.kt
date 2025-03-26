package com.codewithdipesh.habitized.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewithdipesh.habitized.data.local.entity.OneTimeTaskEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.UUID

@Dao
interface OneTimeTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: OneTimeTaskEntity)

    @Query("SELECT * FROM one_time_tasks WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: UUID): OneTimeTaskEntity?

    @Query("SELECT * FROM one_time_tasks where date = :date")
    fun getAllTasks(date: LocalDate): Flow<List<OneTimeTaskEntity>>

    @Query("DELETE FROM one_time_tasks WHERE taskId = :taskId")
    suspend fun deleteTask(taskId: UUID)

    @Query("UPDATE one_time_tasks SET isCompleted = :isCompleted WHERE taskId = :taskId")
    suspend fun updateTaskCompletion(taskId: UUID, isCompleted: Boolean)
}
