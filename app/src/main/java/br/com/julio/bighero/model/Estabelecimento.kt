package br.com.julio.bighero.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Estabelecimento {
    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("bio")
    @Expose
    var bio: String? = null

    @SerializedName("blind")
    @Expose
    var blind = false

    @SerializedName("wheelchair")
    @Expose
    var wheelchair = false

    @SerializedName("hearing")
    @Expose
    var hearing = false

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("__v")
    @Expose
    var v = 0
}