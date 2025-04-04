package com.msl.lookheart.utils

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class SecurePreferences(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("look_heart_secure_prefs", Context.MODE_PRIVATE)

    private val secretKey: SecretKey by lazy {
        getAESKey() ?: generateAESKey()
    }

    private fun generateAESKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                KEYSTORE_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
        )
        return keyGenerator.generateKey()
    }

    private fun getAESKey(): SecretKey? {
        return try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
            keyStore.getKey(KEYSTORE_ALIAS, null) as? SecretKey
        } catch (e: Exception) {
            Log.e("SecurePreferences", "${e.message}")
            null
        }
    }

    fun encrypt(data: String): Pair<String, String> {
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val iv = cipher.iv
        val encryptedData = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        val ivString = Base64.encodeToString(iv, Base64.DEFAULT)
        val encryptedString = Base64.encodeToString(encryptedData, Base64.DEFAULT)

        return Pair(ivString, encryptedString)
    }

    fun decrypt(encryptedData: String, iv: String): String {
        val cipher = Cipher.getInstance(AES_MODE)
        val ivBytes = Base64.decode(iv, Base64.DEFAULT)
        val gcmSpec = GCMParameterSpec(128, ivBytes)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec)

        val encryptedBytes = Base64.decode(encryptedData, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        return String(decryptedBytes, Charsets.UTF_8)
    }


    inline fun <reified T> setValue(key: String, value: T) {
        val stringValue = when (value) {
            is Int, is Long, is Float, is Boolean, is String -> value.toString()
            else -> throw IllegalArgumentException("Unsupported type")
        }

        val (iv, encryptedValue) = encrypt(stringValue)

        sharedPreferences.edit().apply {
            putString("${key}_iv", iv)
            putString(key, encryptedValue)
            apply()
        }
    }

    inline fun <reified T> getValue(key: String, defaultValue: T): T {
        val encryptedData = sharedPreferences.getString(key, null)
        val iv = sharedPreferences.getString("${key}_iv", null)

        if (encryptedData != null && iv != null) {
            val decryptedValue = decrypt(encryptedData, iv)

            return when (T::class) {
                Int::class -> decryptedValue.toInt() as T
                Long::class -> decryptedValue.toLong() as T
                Float::class -> decryptedValue.toFloat() as T
                Boolean::class -> decryptedValue.toBoolean() as T
                String::class -> decryptedValue as T
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }

        return defaultValue
    }

    fun clearAllPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    fun removeKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun getKeys(filterKey: String): Set<String> {
        return sharedPreferences.all.filterKeys { it.startsWith(filterKey) }.keys
    }

    companion object {
        private const val KEYSTORE_ALIAS = "secure_prefs_key"
        private const val AES_MODE = "AES/GCM/NoPadding"
    }
}