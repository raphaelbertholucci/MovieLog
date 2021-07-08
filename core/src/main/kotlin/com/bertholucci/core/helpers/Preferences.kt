package com.bertholucci.core.helpers

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

private const val PREFS_NAME = "prefs_name"
private const val ENCRYPTED_PREFS_NAME = "encrypted_$PREFS_NAME"

class Preferences(context: Context) {

    private val sharedPrefs by lazy {
        context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
    }

    private val encryptedSharedPrefs by lazy {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        EncryptedSharedPreferences.create(
            ENCRYPTED_PREFS_NAME,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun get(): Preferences {
        return this
    }

    fun containsKey(key: String, encrypted: Boolean = false): Boolean {
        val prefs = if (encrypted) sharedPrefs else encryptedSharedPrefs
        return prefs.contains(key)
    }

    fun <T> saveKey(key: String, value: T, encrypted: Boolean = false) {
        val prefs = if (encrypted) sharedPrefs else encryptedSharedPrefs
        when (value) {
            is String -> prefs.edit().putString(key, value as String).apply()
            is Int -> prefs.edit().putInt(key, value as Int).apply()
            is Boolean -> prefs.edit().putBoolean(key, value as Boolean).apply()
            is Float -> prefs.edit().putFloat(key, value as Float).apply()
            is Long -> prefs.edit().putLong(key, value as Long).apply()
        }
    }

    fun getString(
        key: String,
        defaultValue: String = "",
        encrypted: Boolean = false
    ): String {
        val prefs = if (encrypted) sharedPrefs else encryptedSharedPrefs
        return prefs.getString(key, defaultValue) ?: ""
    }

    fun getInt(key: String, defaultValue: Int = 0, encrypted: Boolean = false): Int {
        val prefs = if (encrypted) sharedPrefs else encryptedSharedPrefs
        return prefs.getInt(key, defaultValue)
    }

    fun getBoolean(
        key: String,
        defaultValue: Boolean = false,
        encrypted: Boolean = false
    ): Boolean {
        val prefs = if (encrypted) sharedPrefs else encryptedSharedPrefs
        return prefs.getBoolean(key, defaultValue)
    }

    fun getFloat(
        key: String,
        defaultValue: Float = 0F,
        encrypted: Boolean = false
    ): Float {
        val prefs = if (encrypted) sharedPrefs else encryptedSharedPrefs
        return prefs.getFloat(key, defaultValue)
    }

    fun deleteKey(key: String, encrypted: Boolean = false) {
        val prefs = if (encrypted) sharedPrefs else encryptedSharedPrefs
        prefs.edit().remove(key).apply()
    }
}
