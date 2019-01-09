package com.ebpearls.sample.ui.login

import androidx.room.Entity
import androidx.room.PrimaryKey

data class LoginRequest(val email: String = "hira.shrestha@ebpearls.com", val password: String = "ebpearls123", val login_as: Int = 1)
@Entity(tableName = "user")
data class LoginResponse(
        @PrimaryKey
        val _id: String,
        val name: String,
        val email: String,
        val date_of_birth: String,
        val email_verified_at: String,
        val status: Int,
        val updated_at: String,
        val created_at: String,
        val logged_in_at: String,
        val logged_in_group: Int
)