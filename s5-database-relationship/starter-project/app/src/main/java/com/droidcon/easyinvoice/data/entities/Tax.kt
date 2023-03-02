package com.droidcon.easyinvoice.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taxes")
data class Tax(
    val desc: String,
    val value: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    val taxLabel
        get() = "$desc ($value%)"

    val taxValue
        get() = "$value%"
}