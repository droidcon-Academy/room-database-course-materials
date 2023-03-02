package com.droidcon.easyinvoice.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "invoice_items",
    foreignKeys = [
        ForeignKey(
            entity = Invoice::class,
            parentColumns = ["id"],
            childColumns = ["invoice_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class InvoiceItem(
    val desc: String,
    val qty: Double,
    val price: Double,
    val invoice_id: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    val amount: Double
        get() = qty * price
}