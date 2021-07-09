package br.com.julio.bighero.widgets.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.julio.bighero.databinding.ItemEstabelecimentoBinding
import br.com.julio.bighero.model.Estabelecimento

class EstabelecimentoAdapter(var estabelecimentos : List<Estabelecimento>, var evento: EstabelecimentoAdapter.Evento) : RecyclerView.Adapter<EstabelecimentoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val binding = ItemEstabelecimentoBinding.inflate(layoutInflate)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estabelecimento = estabelecimentos[position]
        holder.binding.txtName.text = estabelecimento.name
        holder.binding.txtAddress.text = estabelecimento.address
        holder.binding.btnEncaminhar.setOnClickListener{ evento.onEstabelecimentoClick(estabelecimento) }
    }

    override fun getItemCount(): Int{
        return estabelecimentos.size
    }

    interface Evento{
        fun onEstabelecimentoClick(estabelecimento: Estabelecimento)
    }
    data class ViewHolder(var binding: ItemEstabelecimentoBinding) : RecyclerView.ViewHolder(binding.root)

}