package com.example.entities.user

data class User(
    val id: Int,
    var name: String?,
    var city: String?,
    var street: String?,
    var apartmentNo: String?,
    var postCode: String?,
    var oauthId: String?
)
