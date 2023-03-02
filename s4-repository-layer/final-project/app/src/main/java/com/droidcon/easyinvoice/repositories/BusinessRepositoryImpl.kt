package com.droidcon.easyinvoice.repositories

import com.droidcon.easyinvoice.data.daos.BusinessDao
import com.droidcon.easyinvoice.data.entities.Business
import kotlinx.coroutines.flow.Flow

class BusinessRepositoryImpl(
    private val dao: BusinessDao
) : BusinessRepository {

    override suspend fun addUpdateBusiness(business: Business) {
        dao.addUpdateBusiness(business)
    }

    override fun getBusinesses(): Flow<List<Business>> {
        return dao.getBusinesses()
    }

    override suspend fun deleteMyBusiness(id: Int?) {
        dao.deleteBusiness(
            Business("", "", "", "").also {
                it.id = id
            }
        )
    }
}