package com.jhj0517.custom_tooltip_window.viewmoels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhj0517.custom_tooltip_window.R
import com.jhj0517.custom_tooltip_window.models.ExampleData


class FirstFragmentViewModel: ViewModel(){

    private val _exampleData = MutableLiveData<List<ExampleData>>()
    val exampleData get() = _exampleData

    init {
        fetchData()
    }

    private fun fetchData() {
        _exampleData.value = listOf(
            ExampleData(
                image = R.drawable.strawberry,
                name = "strawberry",
                desc = """
                    The garden strawberry is a widely grown hybrid species of the genus Fragaria,
                    collectively known as the strawberries, which are cultivated worldwide for their fruit.
                    The fruit is widely appreciated for its characteristic aroma, bright red color,
                    juicy texture, and sweetness. It is consumed in large quantities, either fresh or in
                    such prepared foods as jam, juice, pies, ice cream, milkshakes, and chocolates.
                    """.trimIndent()
            ),
            ExampleData(
                image = R.drawable.grapes,
                name = "grapes",
                desc = """
                    A grape is a fruit, botanically a berry, of the deciduous woody vines of the flowering plant genus Vitis.
                    Grapes are a non-climacteric type of fruit, generally occurring in clusters.
                    The cultivation of grapes began perhaps 8,000 years ago, and the fruit has been used as human food over history.
                    """.trimIndent()
            ),
            ExampleData(
                image = R.drawable.orange,
                name = "orange",
                desc = """
                    An orange, also called sweet orange to distinguish it from the bitter orange Citrus × aurantium,
                    is the fruit of a tree in the family Rutaceae.
                    """.trimIndent()
            ),
            ExampleData(
                image = R.drawable.tomato,
                name = "tomato",
                desc = """
                    The tomato is the edible berry of the plant Solanum lycopersicum, commonly known as the tomato plant.
                    The species originated in western South America, Mexico, and Central America.
                    The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derives.
                    """.trimIndent()
            )
        )
    }
}