package br.stone.mobiletraining.samilasantos.data.service.common

import com.google.gson.annotations.SerializedName

data class Fact(
    val category: List<String>?,
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)