package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber): Int

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber): Int

    @Query("SELECT * FROM subscriber_data_table")
    fun selectAllList(): LiveData<List<Subscriber>>

    @Query("DELETE  FROM subscriber_data_table")
    suspend fun deleteAllList(): Int
}