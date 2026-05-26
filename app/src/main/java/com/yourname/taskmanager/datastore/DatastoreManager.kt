package com.yourname.taskmanager.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val DATASTORE_COLORS = "colors"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_COLORS)

class DatastoreManager @Inject constructor(val context: Context) {
    suspend fun saveStringPreference(
        value: String,
        key: String
    ) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey(key)] = value
        }
    }

    fun getStringPreference(
        key: String,
        defValue: String
    ) = context.dataStore.data.map { pref -> pref[stringPreferencesKey(key)] ?: defValue }

    companion object {
        const val TITLE_COLOR = "title_color"
    }
}