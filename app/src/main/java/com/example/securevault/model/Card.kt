package com.example.securevault.model

data class Card(
    val title: String,
    val number: String,
    val cvv: String,
    val expiryDate: String
)