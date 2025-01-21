package com.example.pdpapp.contentprovider.storage.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pdpapp.contentprovider.storage.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAll(): Cursor

    @Insert
    fun insert(product: Product): Long

    @Query("DELETE FROM products WHERE id = :productId")
    fun deleteById(productId: Long): Int

    @Update
    fun update(product: Product): Int
}