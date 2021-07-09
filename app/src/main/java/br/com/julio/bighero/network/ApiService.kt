package br.com.julio.bighero.network

import br.com.julio.bighero.model.Estabelecimento
import br.com.julio.bighero.model.PacoteJSON
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("establishment")
    fun listarEstabelecimentos(): Call<PacoteJSON<List<Estabelecimento?>?>?>

    @GET("establishment/{id}")
    fun listarEstabelecimento(@Path("id") id: Int): Call<PacoteJSON<List<Estabelecimento?>?>?>?

    @POST("establishment/create")
    fun inserirEstabelecimento(@Body estabelecimento: Estabelecimento?): Call<PacoteJSON<Estabelecimento?>?>?

}