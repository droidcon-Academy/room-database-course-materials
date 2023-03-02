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

package com.droidcon.easyinvoice.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.droidcon.easyinvoice.data.utils.currentDateTime

@Entity(
    tableName = "invoices",
    foreignKeys = [
        ForeignKey(
            entity = Business::class,
            parentColumns = ["id"],
            childColumns = ["business_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customer_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Tax::class,
            parentColumns = ["id"],
            childColumns = ["tax_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Invoice(
    val business_id: Int,
    val customer_id: Int,
    val tax_id: Int,
    val created_at: Long = currentDateTime,
    var is_paid: Boolean = false,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}