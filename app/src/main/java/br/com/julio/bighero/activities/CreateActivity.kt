package br.com.julio.bighero.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.julio.bighero.databinding.ActivityCreateBinding
import br.com.julio.bighero.databinding.FragmentHomeBinding
import br.com.julio.bighero.model.Estabelecimento
import br.com.julio.bighero.model.PacoteJSON
import br.com.julio.bighero.network.NetworkManager
import br.com.julio.bighero.widgets.adapters.EstabelecimentoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {
    private var binding: ActivityCreateBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnEvento?.setOnClickListener{onCadastrarEstabelecimento()}
    }

    private fun onCadastrarEstabelecimento() {
        var sucesso = true
        val estabelecimento = Estabelecimento()
        estabelecimento.name = binding?.edtNome?.text.toString()
        estabelecimento.address = binding?.edtAddress?.text.toString()
        estabelecimento.bio = binding?.edtAbout?.text.toString()
        estabelecimento.blind = binding?.Cego?.isChecked!!
        estabelecimento.wheelchair = binding?.Cadeirante?.isChecked!!
        estabelecimento.hearing = binding?.Surdo?.isChecked!!
        if(estabelecimento.name!!.isEmpty()){
            binding!!.edtNome.setError("Campo Obrigatorio")
            sucesso = false
        }
        if(sucesso){
            val call = NetworkManager.service.inserirEstabelecimento(estabelecimento)

            // Chamada assíncrona para o servidor
            // - enqueue(Callback) -> callback tem o retorno do servidor
            //                     -> pode vir a qualquer momento!
            call?.enqueue(object : Callback<PacoteJSON<Estabelecimento?>?> {
                override fun onResponse(
                    call: Call<PacoteJSON<Estabelecimento?>?>,
                    response: Response<PacoteJSON<Estabelecimento?>?>
                ) {
                    onTratarResposta(response.body())
                }

                override fun onFailure(call: Call<PacoteJSON<Estabelecimento?>?>, t: Throwable) {
                    Log.e("TAG", "erro fatal", t)
                }

            })
        }
    }

    private fun onTratarResposta(body: PacoteJSON<Estabelecimento?>?) {
        if(body === null){
            Toast.makeText(this, "Erro ao criar o estabelecimento", Toast.LENGTH_LONG).show()
        } else {
            SharedUtil.setEstabelecimento(CreateActivity@this, body.objeto!!)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }


}