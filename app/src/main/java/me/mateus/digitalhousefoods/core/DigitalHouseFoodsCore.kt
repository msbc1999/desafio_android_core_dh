package me.mateus.digitalhousefoods.core

import me.mateus.digitalhousefoods.R
import java.io.Serializable
import java.time.LocalTime


class Restaurante : Serializable {
    var nome: String = ""
    var endereco: String = ""
    var horarioFechar: LocalTime = LocalTime.of(0, 0)
    var imagemLogo: Int = 0
    var cardapio: MutableList<RestauranteItem> = mutableListOf()
}

class RestauranteItem : Serializable {
    var nome: String = ""
    var descricao: String = ""
    var imagem: Int = 0
}


fun criarConteudoExemplo() = mutableListOf<Restaurante>().apply {

    val criarCardapio = fun(img: Int): MutableList<RestauranteItem> {
        return mutableListOf<RestauranteItem>().apply {
            (1..10).onEach {
                add(RestauranteItem().apply {
                    nome = "Salada com molho Gengibre "
                    descricao =
                        "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusant doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis."
                    imagem = img
                })
            }
        }
    }

    add(Restaurante().apply {
        imagemLogo = R.drawable.image1
        nome = "Tony Roma's"
        endereco = "Av. Lavandisca, 717 - Indianópolis, São Paulo"
        horarioFechar = LocalTime.of(22, 0)
        cardapio = criarCardapio(R.drawable.image3)
    })
    add(Restaurante().apply {
        imagemLogo = R.drawable.image3
        nome = "Aoyama - Moema"
        endereco = "Alameda dos Arapanés, 532 - Moema"
        horarioFechar = LocalTime.of(0, 0)
        cardapio = criarCardapio(R.drawable.image1)
    })
    add(Restaurante().apply {
        imagemLogo = R.drawable.image4
        nome = "Outback - Moema"
        endereco = "Av. Moaci, 187, 187 - Moema, São Paulo"
        horarioFechar = LocalTime.of(0, 0)
        cardapio = criarCardapio(R.drawable.image3)
    })
    add(Restaurante().apply {
        imagemLogo = R.drawable.image2
        nome = "Sí Señor!"
        endereco = "Alameda Jauaperi, 626 - Moema"
        horarioFechar = LocalTime.of(1, 0)
        cardapio = criarCardapio(R.drawable.image4)
    })
}