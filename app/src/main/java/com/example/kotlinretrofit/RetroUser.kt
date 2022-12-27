package com.example.kotlinretrofit

import com.google.gson.annotations.SerializedName

data class RetroUser(@field:SerializedName("email")var email: String, @field:SerializedName("name") var name: String?)