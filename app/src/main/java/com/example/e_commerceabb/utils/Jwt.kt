package com.example.e_commerceabb.utils

import android.os.Build
import org.json.JSONObject
import java.util.*

object Jwt {

    fun getUserId(token: String): String {
        val mDecode = decodeToken(token)
        val user = JSONObject(mDecode)
        return user.getString("id")
    }

    private fun decodeToken(jwt: String): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return "Requires SDK 26"
        val parts = jwt.split(".")
        return try {
            val charset = charset("UTF-8")
            val payload =
                String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
            payload
        } catch (e: Exception) {
            "Error parsing JWT: $e"
        }
    }
}