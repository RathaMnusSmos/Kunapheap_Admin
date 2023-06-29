package com.rathalove.kunapheapadmin.RoomData.UserLogIn

import androidx.room.*


@Dao
interface UserDao {
    @Query("select * from user_tbl")
    fun getAllUser():List<UserData>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewUser(user: UserData): Long
    @Delete
    fun deleteUser(user: UserData)
}