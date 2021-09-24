package com.bertholucci.data.preferences


import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

private const val PREFS_NAME = "prefs_name"
private const val ENCRYPTED_PREFS_NAME = "encrypted_$PREFS_NAME"

class MovieLogEncryptedPrefs(private val context: Context) {

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

    fun get(): MovieLogEncryptedPrefs {
        return this
    }

    fun containsKey(key: String): Boolean {
        return encryptedSharedPrefs.contains(key)
    }

    fun <T> saveKey(key: String, value: T) {
        when (value) {
            is String -> encryptedSharedPrefs.edit().putString(key, value as String).apply()
            is Int -> encryptedSharedPrefs.edit().putInt(key, value as Int).apply()
            is Boolean -> encryptedSharedPrefs.edit().putBoolean(key, value as Boolean).apply()
            is Float -> encryptedSharedPrefs.edit().putFloat(key, value as Float).apply()
            is Long -> encryptedSharedPrefs.edit().putLong(key, value as Long).apply()
        }
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return encryptedSharedPrefs.getString(key, defaultValue) ?: ""
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return encryptedSharedPrefs.getInt(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return encryptedSharedPrefs.getBoolean(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float = 0F): Float {
        return encryptedSharedPrefs.getFloat(key, defaultValue)
    }

    fun deleteKey(key: String) {
        encryptedSharedPrefs.edit().remove(key).apply()
    }
}
