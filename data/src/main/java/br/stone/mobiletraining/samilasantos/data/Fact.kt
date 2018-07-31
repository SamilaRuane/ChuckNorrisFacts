package br.stone.mobiletraining.samilasantos.data

import com.google.gson.annotations.SerializedName

data class Fact(@SerializedName("icon_url")
                val iconUrl: String,
                val id: String,
                val url: String,
                val value: String)