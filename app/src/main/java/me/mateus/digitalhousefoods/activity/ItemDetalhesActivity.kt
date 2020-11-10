package me.mateus.digitalhousefoods.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_detalhes.*
import me.mateus.digitalhousefoods.R
import me.mateus.digitalhousefoods.core.RestauranteItem

class ItemDetalhesActivity : AppCompatActivity() {

    private lateinit var restauranteItem: RestauranteItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detalhes)

        restauranteItem = intent.getSerializableExtra("restaurante_item") as RestauranteItem


        restauranteItem.let {
            txtItemNome.text = it.nome
            txtItemDescricao.text = it.descricao
            imgItemImagem.setImageResource(it.imagem)
        }

        imgItemVoltar.setOnClickListener {
            finish()
        }
    }
}