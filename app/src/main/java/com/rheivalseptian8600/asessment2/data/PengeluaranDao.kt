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

    @Query("SELECT * FROM pengeluaran WHERE isDeleted = 0 ORDER BY tanggal DESC")
    fun getSemuaPengeluaran(): Flow<List<Pengeluaran>>

    @Query("SELECT * FROM pengeluaran WHERE isDeleted = 1 ORDER BY tanggal DESC")
    fun getDeletedPengeluaran(): Flow<List<Pengeluaran>>

    @Query("UPDATE pengeluaran SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Int)

    @Query("UPDATE pengeluaran SET isDeleted = 0 WHERE id = :id")
    suspend fun restore(id: Int)

    @Query("DELETE FROM pengeluaran WHERE id = :id")
    suspend fun deletePermanent(id: Int)
}