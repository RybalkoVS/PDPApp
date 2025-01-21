package com.example.pdpapp.contentprovider.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pdpapp.contentprovider.storage.dao.ProductDao
import com.example.pdpapp.contentprovider.storage.model.Product

@Database(entities = [Product::class], exportSchema = false, version = 1)
abstract class ProductDataBase : RoomDatabase() {
    companion object {
        const val DB_NAME = "products"

        fun init(context: Context): ProductDataBase {
            return Room.databaseBuilder(
                context,
                ProductDataBase::class.java, DB_NAME
            ).build()
        }
    }

    abstract fun productDao(): ProductDao
}