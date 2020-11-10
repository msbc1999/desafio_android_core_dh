package me.mateus.digitalhousefoods.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.view.*
import me.mateus.digitalhousefoods.R
import me.mateus.digitalhousefoods.activity.RestauranteActivity
import me.mateus.digitalhousefoods.core.Restaurante
import me.mateus.digitalhousefoods.core.criarConteudoExemplo

class RestaurantesFragment : Fragment() {

    private val conteudo = criarConteudoExemplo()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false).apply {
            rvRestaurantes.adapter = RestauranteAdapter(conteudo).apply {
                restauranteClickListener = object : RestauranteClickListener {
                    override fun onRestauranteClick(r: Restaurante, rvh: RestauranteViewHolder) {
                        // TODO
//                        var a =
//                            RestaurantesFragmentDirections.actionRestaurantesFragmentToRestauranteDetalhesFragment(
//                                r.nome,
//                                r
//                            )
//                        findNavController().navigate(a)


                        startActivity(
                            Intent(context, RestauranteActivity::class.java).apply {
                                putExtra("restaurante", r)
                            },
                            ActivityOptions.makeSceneTransitionAnimation(
                                activity,
//                                Pair(rvh.cpLogo as View, "logo_restaurante"),
                                Pair(
                                    activity?.findViewById(R.id.appBarMainImage) as View,
                                    "transition_toolbar_logo"
                                )
//                                Pair(
//                                    activity?.findViewById(R.id.appBarMain) as View,
//                                    "transition_toolbar"
//                                )
                            ).toBundle()
                        )
                    }
                }
            }
            rvRestaurantes.layoutManager = LinearLayoutManager(context)
            rvRestaurantes.setHasFixedSize(true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RestaurantesFragment()
    }
}


interface RestauranteClickListener {
    fun onRestauranteClick(restaurante: Restaurante, restauranteViewHolder: RestauranteViewHolder)
}


class RestauranteAdapter(private val restaurantes: List<Restaurante>) :
    RecyclerView.Adapter<RestauranteViewHolder>() {

    var restauranteClickListener = object : RestauranteClickListener {
        override fun onRestauranteClick(r: Restaurante, rvh: RestauranteViewHolder) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        return RestauranteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_card_restaurante,
                parent,
                false
            ),
            this
        )
    }

    override fun getItemCount() = restaurantes.size

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        holder.apply {
            restaurante = restaurantes[position]
            updateViewData()
        }
    }

}


class RestauranteViewHolder(
    view: View,
    private val adapter: RestauranteAdapter
) : RecyclerView.ViewHolder(view) {

    var cpLogo: ImageView = view.findViewById(R.id.imgRestauranteLogo)
    var cpNome: TextView = view.findViewById(R.id.lblRestauranteNome)
    var cpEndereco: TextView = view.findViewById(R.id.lblRestauranteEndereco)
    var cpHorario: TextView = view.findViewById(R.id.lblRestauranteHorario)

    var restaurante: Restaurante = Restaurante()

    init {
        view.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                adapter.restauranteClickListener.onRestauranteClick(
                    restaurante,
                    this
                )
            }
        }
    }

    fun updateViewData() {
        cpLogo.setImageResource(restaurante.imagemLogo)
        cpNome.text = restaurante.nome
        cpEndereco.text = restaurante.endereco
        cpHorario.text = "Fecha às ${restaurante.horarioFechar.run {
            "${if (hour < 10) "0${hour}" else "${hour}"}:${if (minute < 10) "0${minute}" else "${minute}"}"
        }}"
    }

}


