package br.com.julio.bighero.model

import com.google.gson.annotations.SerializedName

class PacoteJSON<T> {
    var error: String? = null
    @SerializedName("establishment")
    var objeto: T? = null
}