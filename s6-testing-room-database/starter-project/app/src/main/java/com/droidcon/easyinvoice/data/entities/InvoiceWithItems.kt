package com.droidcon.easyinvoice.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class InvoiceWithItems(
    @Embedded val invoice: Invoice,
    @Relation(
        parentColumn = "business_id",
        entityColumn = "id"
    )
    val business: Business,
    @Relation(
        parentColumn = "customer_id",
        entityColumn = "id"
    )
    val customer: Customer,
    @Relation(
        parentColumn = "tax_id",
        entityColumn = "id"
    )
    val tax: Tax,
    @Relation(
        parentColumn = "id",
        entityColumn = "invoice_id"
    )
    val items: List<InvoiceItem>
) {

    val totalAmount: Double
        get() = items.sumOf { it.qty * it.price }

    val taxAmount: Double
        get() = totalAmount * tax.value / 100

    val invoiceAmount: Double
        get() = totalAmount + taxAmount
}
