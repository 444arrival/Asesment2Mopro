package com.rheivalseptian8600.asessment2.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rheivalseptian8600.asessment2.data.PengeluaranDb
import com.rheivalseptian8600.asessment2.model.Pengeluaran
import com.rheivalseptian8600.asessment2.util.SettingsDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = PengeluaranDb.getInstance(application).dao
    private val settings = SettingsDataStore(application)

    val data: StateFlow<List<Pengeluaran>> = dao.getSemuaPengeluaran()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    val isGridLayout: StateFlow<Boolean> = settings.layoutFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    val isDarkMode: StateFlow<Boolean> = settings.darkModeFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )

    fun setGridLayout(isGrid: Boolean) {
        viewModelScope.launch { settings.saveLayoutSetting(isGrid) }
    }

    fun setDarkMode(isDark: Boolean) {
        viewModelScope.launch { settings.saveDarkModeSetting(isDark) }
    }

    fun simpanData(nama: String, nominal: String) {
        val item = Pengeluaran(nama = nama, nominal = nominal.toIntOrNull() ?: 0)
        viewModelScope.launch(Dispatchers.IO) { dao.insert(item) }
    }

    fun hapusData(item: Pengeluaran) {
        viewModelScope.launch(Dispatchers.IO) { dao.delete(item) }
    }

    fun updateData(id: Int, nama: String, nominal: String) {
        val item = Pengeluaran(id = id, nama = nama, nominal = nominal.toIntOrNull() ?: 0)
        viewModelScope.launch(Dispatchers.IO) { dao.update(item) }
    }
}