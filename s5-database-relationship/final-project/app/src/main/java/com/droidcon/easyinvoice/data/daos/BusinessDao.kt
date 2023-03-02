package com.droidcon.easyinvoice.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidcon.easyinvoice.data.entities.Business
import kotlinx.coroutines.flow.Flow


@Dao
interface BusinessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpdateBusiness(business: Business)

    @Query("SELECT * FROM businesses")
    fun getBusinesses(): Flow<List<Business>>

    @Delete
    suspend fun deleteBusiness(business: Business)
}