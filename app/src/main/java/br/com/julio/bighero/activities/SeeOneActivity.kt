package br.com.julio.bighero.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import br.com.julio.bighero.databinding.ActivitySeeoneBinding

class SeeOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySeeoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SharedUtil.getEstabelecimento(this)
        val estabelecimento = SharedUtil.getEstabelecimento(this)
        binding.txtName.text = "Nome: ${estabelecimento.name}"
        binding.txtAddress.text = "Endereço: ${estabelecimento.address}"
        binding.txtBio.text = "Descrição: ${estabelecimento.bio}"
        if(!estabelecimento.blind) {
            binding.txtBlind.text = "Não possui acesso para deficientes visuais"
        } else {
            binding.txtBlind.text = "Possui acesso para deficientes visuais"
        }
        if(!estabelecimento.wheelchair) {
            binding.txtWheelchair.text = "Não possui acesso para deficientes motores"
        } else {
            binding.txtWheelchair.text = "Possui acesso para deficientes motores"
        }
        if(!estabelecimento.hearing) {
            binding.txtHearing.text = "Não possui acesso para deficientes auditivos"
        } else {
            binding.txtHearing.text = "Possui acesso para deficientes auditivos"
        }
    }
}