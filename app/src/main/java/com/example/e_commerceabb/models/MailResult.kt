package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class MailResult(
    @SerializedName("accepted")
    val accepted: List<String>,
    @SerializedName("envelope")
    val envelope: Envelope,
    @SerializedName("envelopeTime")
    val envelopeTime: Int,
    @SerializedName("messageId")
    val messageId: String,
    @SerializedName("messageSize")
    val messageSize: Int,
    @SerializedName("messageTime")
    val messageTime: Int,
    @SerializedName("rejected")
    val rejected: List<Any>,
    @SerializedName("response")
    val response: String
)