package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class User (var userName: String, var password: String) {
}