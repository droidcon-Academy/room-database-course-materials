package com.droidcon.easyinvoice.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidcon.easyinvoice.data.entities.Business
import com.droidcon.easyinvoice.data.entities.Customer
import com.droidcon.easyinvoice.data.entities.Tax
import kotlinx.coroutines.flow.Flow

@Dao
interface TaxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpdateTax(tax: Tax)

    @Query("SELECT * FROM taxes")
    fun getTaxes(): Flow<List<Tax>>

    @Delete
    suspend fun deleteTax(tax: Tax)
}