package com.faysal.assignementapp.data.models

data class User(
    val email: String,
    val location: Location,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture
)