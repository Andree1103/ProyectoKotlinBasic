package com.cibertec.app.model

import com.google.gson.annotations.SerializedName

data class ResultApi(
@SerializedName("CodigoMensaje")
val codeMessage : Int,
@SerializedName("ResultadoMensaje")
val resultMessage: String
)
