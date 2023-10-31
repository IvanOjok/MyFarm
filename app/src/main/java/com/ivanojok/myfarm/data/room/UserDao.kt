package com.ivanojok.myfarm.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ivanojok.myfarm.data.model.AuthResponse

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: AuthResponse)

    @Query("Select * from AuthResponse")
    fun getUsers(): List<AuthResponse>
}