package com.bertholucci.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "movie-log-ds"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class MovieLogPrefs(val context: Context) {

    suspend fun saveKey(key: String, value: String) {
        val wrappedKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences -> preferences[wrappedKey] = value }
    }

    suspend fun saveKey(key: String, value: Int) {
        val wrappedKey = intPreferencesKey(key)
        context.dataStore.edit { preferences -> preferences[wrappedKey] = value }
    }

    suspend fun saveKey(key: String, value: Boolean) {
        val wrappedKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences -> preferences[wrappedKey] = value }
    }

    suspend fun saveKey(key: String, value: Double) {
        val wrappedKey = doublePreferencesKey(key)
        context.dataStore.edit { preferences -> preferences[wrappedKey] = value }
    }

    suspend fun getStringKey(key: String, default: String = ""): String {
        val wrappedKey = stringPreferencesKey(key)
        val value: Flow<String> = context.dataStore.data.map { preferences ->
            preferences[wrappedKey] ?: default
        }
        return value.first()
    }

    suspend fun getIntKey(key: String, default: Int = 0): Int {
        val wrappedKey = intPreferencesKey(key)
        val value: Flow<Int> = context.dataStore.data.map { preferences ->
            preferences[wrappedKey] ?: default
        }
        return value.first()
    }

    suspend fun getBooleanKey(key: String, default: Boolean = false): Boolean {
        val wrappedKey = booleanPreferencesKey(key)
        val value: Flow<Boolean> = context.dataStore.data.map { preferences ->
            preferences[wrappedKey] ?: default
        }
        return value.first()
    }

    suspend fun getDoubleKey(key: String, default: Double = 0.0): Double {
        val wrappedKey = doublePreferencesKey(key)
        val value: Flow<Double> = context.dataStore.data.map { preferences ->
            preferences[wrappedKey] ?: default
        }
        return value.first()
    }
}