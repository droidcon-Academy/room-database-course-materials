package com.droidcon.easyinvoice.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.droidcon.easyinvoice.data.daos.BusinessDao
import com.droidcon.easyinvoice.data.daos.CustomerDao
import com.droidcon.easyinvoice.data.daos.InvoiceDao
import com.droidcon.easyinvoice.data.daos.TaxDao
import com.droidcon.easyinvoice.data.entities.*

@Database(
    entities = [Business::class, Customer::class, Tax::class, Invoice::class, InvoiceItem::class],
    version = 1
)
abstract class EasyInvoiceDatabase: RoomDatabase() {

    abstract fun getBusinessDao(): BusinessDao
    abstract fun getCustomerDao(): CustomerDao
    abstract fun getTaxDao(): TaxDao
    abstract fun getInvoiceDao(): InvoiceDao

    companion object {
        private const val DB_NAME = "easy_invoice_db"

        @Volatile
        private var instance: EasyInvoiceDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            EasyInvoiceDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()

    }
}