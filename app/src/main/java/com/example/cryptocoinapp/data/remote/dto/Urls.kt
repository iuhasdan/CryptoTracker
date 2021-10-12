package com.example.cryptocoinapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Urls(
    val announcement: List<Any>,
    val chat: List<Any>,
    val explorer: List<String>,
    @SerializedName("message_board")
    val messageBoard: List<String>,
    val reddit: List<String>,
    @SerializedName("source_code")
    val sourceCode: List<String>,
    @SerializedName("technical_doc")
    val technicalDoc: List<String>,
    val twitter: List<Any>,
    val website: List<String>
)