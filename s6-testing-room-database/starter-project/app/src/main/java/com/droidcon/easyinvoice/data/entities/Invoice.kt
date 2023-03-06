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