package com.example.pdpapp.contentprovider.storage.model

import android.content.ContentValues
import android.database.Cursor
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val TABLE_NAME = "products"
private const val COLUMN_ID = "id"
private const val COLUMN_NAME = "product_name"
private const val COLUMN_PRICE = "product_price"

@Entity(tableName = TABLE_NAME)
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int = 0,
    @ColumnInfo(name = COLUMN_NAME) val name: String = "",
    @ColumnInfo(name = COLUMN_PRICE) val price: Double = 0.0
) {

    companion object {
        fun fromContentValues(values: ContentValues): Product? {
            return if (values.containsKey(COLUMN_ID)
                && values.containsKey(COLUMN_NAME)
                && values.containsKey(COLUMN_PRICE)
            ) {
                Product(
                    id = values.getAsInteger(COLUMN_ID),
                    name = values.getAsString(COLUMN_NAME),
                    price = values.getAsDouble(COLUMN_PRICE)
                )
            } else null
        }

        fun fromCursor(cursor: Cursor): Product? {
            val idColumn = cursor.getColumnIndex(COLUMN_ID)
            val nameColumn = cursor.getColumnIndex(COLUMN_NAME)
            val priceColumn = cursor.getColumnIndex(COLUMN_PRICE)
            return if (idColumn >= 0 && nameColumn >= 0 && priceColumn >= 0) {
                Product(
                    id = cursor.getInt(idColumn),
                    name = cursor.getString(nameColumn),
                    price = cursor.getDouble(priceColumn)
                )
            } else null
        }
    }
}

fun Product.toContentValues(): ContentValues {
    return ContentValues().apply {
        put(COLUMN_ID, id)
        put(COLUMN_NAME, name)
        put(COLUMN_PRICE, price)
    }
}