package com.puj.admincenter.dto.users

data class UpdateUserPassword(
    val username: String,
    val password: String,
    val newPassword: String
)