package br.com.julio.bighero.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.julio.bighero.activities.CreateActivity
import br.com.julio.bighero.databinding.FragmentHomeBinding
import br.com.julio.bighero.model.Estabelecimento
import br.com.julio.bighero.model.PacoteJSON
import br.com.julio.bighero.network.NetworkManager
import br.com.julio.bighero.widgets.adapters.EstabelecimentoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import br.com.julio.bighero.activities.SeeOneActivity
import br.com.julio.bighero.activities.SharedUtil
import kotlinx.android.synthetic.main.item_estabelecimento.view.*


class HomeFragment : Fragment() {

    // Recebe uma lista para armazenar estabelecimentos
    private var estabelecimentos: MutableList<Estabelecimento> = ArrayList<Estabelecimento>()
    // Define o layout principal
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Cria a view usando data binding
        // setar o layout
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root!!
        binding?.recycler?.layoutManager = LinearLayoutManager(context)
        binding?.recycler?.adapter =
            EstabelecimentoAdapter(estabelecimentos, object : EstabelecimentoAdapter.Evento {
                override fun onEstabelecimentoClick(estabelecimento: Estabelecimento) {
                    // SharedUtil.getEstabelecimento(context, estabelecimento)
                    view.btnEncaminhar.setOnClickListener{
                        val intent = Intent(context, SeeOneActivity::class.java)
                        startActivity(intent)
                    }
                }
            })
        binding?.floatingActionButton?.setOnClickListener{
            openCreateActivity.launch(Intent(context, CreateActivity::class.java))
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Realiza uma chamada para o servidor
        val call = NetworkManager.service.listarEstabelecimentos()

        // Chamada assíncrona para o servidor
        // - enqueue(Callback) -> callback tem o retorno do servidor
        //                     -> pode vir a qualquer momento!
        call?.enqueue(object : Callback<PacoteJSON<List<Estabelecimento?>?>?> {
            override fun onResponse(
                call: Call<PacoteJSON<List<Estabelecimento?>?>?>,
                response: Response<PacoteJSON<List<Estabelecimento?>?>?>
            ) {
                if (response.isSuccessful) {
                    for (estabelecimento in response.body()?.objeto!!) {
                        if (estabelecimento !== null) {
                            estabelecimentos.add(estabelecimento)
                        }
                    }
                    binding?.recycler?.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<PacoteJSON<List<Estabelecimento?>?>?>, t: Throwable) {
                Log.e("TAG", "erro fatal", t)
            }

        })
    }

    var openCreateActivity = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            var estabelecimento = SharedUtil.getEstabelecimento(requireContext())
            if(estabelecimento.id !== null){
                estabelecimentos.add(estabelecimento)
                binding?.recycler?.adapter?.notifyDataSetChanged()
                SharedUtil.setEstabelecimento(requireContext(), Estabelecimento())
            }
        }
    }


    /**
     * Método estático para realizar a construção do fragmento
     * Pertence a classe e não aos objetos - operações estáticas (static)
     */
    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}