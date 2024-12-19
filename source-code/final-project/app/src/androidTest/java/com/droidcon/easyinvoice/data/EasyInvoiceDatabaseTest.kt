package com.droidcon.easyinvoice.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droidcon.easyinvoice.data.entities.Business
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EasyInvoiceDatabaseTest {

    private lateinit var db: EasyInvoiceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, EasyInvoiceDatabase::class.java).build()
    }

    @Test
    fun testAddAndRetrieveBusiness() {
        val name = "business1"
        val address = "address1"
        val phone = "1232343454"
        val email = "email@email.com"

        val business = Business(name, address, phone, email)

        val business1 = runBlocking {
            db.getBusinessDao().addUpdateBusiness(business)
            db.getBusinessDao().getBusinesses().firstOrNull()
        }

        assert(business1?.contains(business) == true)
    }

    @After
    fun cleanUp() {
        db.close()
    }

}