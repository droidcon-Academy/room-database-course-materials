package com.droidcon.easyinvoice.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidcon.easyinvoice.data.entities.Business
import com.droidcon.easyinvoice.data.entities.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpdateCustomer(customer: Customer)

    @Query("SELECT * FROM customers")
    fun getCustomers(): Flow<List<Customer>>

    @Delete
    suspend fun deleteCustomer(customer: Customer)
}