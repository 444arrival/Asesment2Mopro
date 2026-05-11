package com.rheivalseptian8600.asessment2.data

import androidx.room.*
import com.rheivalseptian8600.asessment2.model.Pengeluaran
import kotlinx.coroutines.flow.Flow

@Dao
interface PengeluaranDao {
    @Insert
    suspend fun insert(pengeluaran: Pengeluaran)

    @Update
    suspend fun update(pengeluaran: Pengeluaran)

    @Delete
    suspend fun delete(pengeluaran: Pengeluaran)

    @Query("SELECT * FROM pengeluaran ORDER BY tanggal DESC")
    fun getSemuaPengeluaran(): Flow<List<Pengeluaran>>
}