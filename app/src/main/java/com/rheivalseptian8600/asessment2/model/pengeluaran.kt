package com.rheivalseptian8600.asessment2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengeluaran")
data class Pengeluaran(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val nominal: Int,
    val tanggal: Long = System.currentTimeMillis()
)