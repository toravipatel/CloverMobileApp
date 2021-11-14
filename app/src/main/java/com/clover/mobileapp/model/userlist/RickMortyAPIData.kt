package com.clover.mobileapp.model


import com.google.gson.annotations.SerializedName

data class RickMortyAPIData(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)