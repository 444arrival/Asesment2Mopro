package com.rheivalseptian8600.asessment2.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {
    private val IS_GRID_LAYOUT = booleanPreferencesKey("is_grid_layout")

    val layoutFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_GRID_LAYOUT] ?: false
    }

    suspend fun saveLayoutSetting(isGridLayout: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_GRID_LAYOUT] = isGridLayout
        }
    }
}