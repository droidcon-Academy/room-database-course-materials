package com.droidcon.easyinvoice.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "businesses")
data class Business(
    val name: String,
    val address: String,
    val phone: String,
    val email: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    val completeAddress: String
        get() = "$address\nphone: $phone\nemail: $email"
}
