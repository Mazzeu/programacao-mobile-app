package br.com.julio.bighero.activities

import android.content.Context
import com.google.gson.Gson
import android.util.Log
import br.com.julio.bighero.model.Estabelecimento

object SharedUtil {
    private const val CONFIG_FILE = "bighero.config"
    private const val KEY_ESTABELECIMENTO = "ESTABELECIMENTO"

    fun setEstabelecimento(context: Context, estabelecimento: Estabelecimento) {
        val editor = context.getSharedPreferences(CONFIG_FILE, Context.MODE_PRIVATE).edit()

        val gson = Gson()
        val json = gson.toJson(estabelecimento)

        Log.d("TAG", json)

        editor.putString(KEY_ESTABELECIMENTO, json)
        editor.apply()
    }

    fun getEstabelecimento(context: Context) : Estabelecimento {
        val preferences = context.getSharedPreferences(CONFIG_FILE, Context.MODE_PRIVATE)
        val json = preferences.getString(KEY_ESTABELECIMENTO, "")

        val gson = Gson()
        val contato: Estabelecimento = gson.fromJson(json, Estabelecimento::class.java)

        return contato
    }
}