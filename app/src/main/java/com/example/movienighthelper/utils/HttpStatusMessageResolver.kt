package com.example.movienighthelper.utils

object HttpStatusMessageResolver {
    fun resolveMessage(code: Int): String {
        return when (code) {
            200 -> "OK"
            201 -> "Created"
            204 -> "No Content"
            400 -> "Bad Request"
            401 -> "Unauthorized"
            403 -> "Forbidden"
            404 -> "Not Found"
            500 -> "Internal Server Error"
            503 -> "Service Unavailable"
            504 -> "Gateway Timeout"
            else -> "Unknown Status Code"
        }
    }
}