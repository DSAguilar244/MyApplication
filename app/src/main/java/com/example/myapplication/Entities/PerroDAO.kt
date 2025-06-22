package com.example.myapplication.Entities
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface PerroDAO {
    @Query("Select * from perros") suspend fun all():List<Perro>
    @Insert suspend fun store(perro: Perro):Long
    @Query("SELECT * FROM perros WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): Perro?
    @Update
    suspend fun update(perro: Perro): Int
    @Delete
    suspend fun delete(perro: Perro): Int
}