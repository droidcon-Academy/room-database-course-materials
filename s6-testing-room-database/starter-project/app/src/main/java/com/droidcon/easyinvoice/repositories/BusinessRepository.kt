package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.entities.Business
import kotlinx.coroutines.flow.Flow

interface BusinessRepository {
    suspend fun addUpdateBusiness(business: Business)
    fun getBusinesses(): Flow<List<Business>>
    suspend fun deleteMyBusiness(id: Int?)
}