package br.com.julio.bighero.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.julio.bighero.model.Estabelecimento
import br.com.julio.bighero.model.PacoteJSON
import br.com.julio.bighero.network.NetworkManager
import br.com.julio.bighero.R
import br.com.julio.bighero.databinding.ActivityMainBinding
import retrofit2.Call

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.julio.bighero.databinding.FragmentHomeBinding
import br.com.julio.bighero.fragments.HomeFragment
import br.com.julio.bighero.widgets.adapters.EstabelecimentoAdapter

import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var fragmentHome: HomeFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // puxa o layout para ser utilizado
        val binding = ActivityMainBinding.inflate(layoutInflater)
        // setar view principal
        setContentView(binding?.root)
        // cria uma nova instancia de homefragment
        fragmentHome = HomeFragment.newInstance()
        // trocar o fragmento
        trocarFragmento(fragmentHome!!)
        Log.e("Error", "Erro na chamada")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_create -> {
                val intent = Intent(this, CreateActivity::class.java)
                ActivityResultContracts.StartActivityForResult()
                return true
            }
        }

        return false
    }

    private fun trocarFragmento(fragmento: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.replace(R.id.container, fragmento)
        fragmentTransaction.commit()
    }

}
