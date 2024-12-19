/*
 * Copyright (c) 2022. Droidcon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
abstract class EasyInvoiceDatabase : RoomDatabase() {

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