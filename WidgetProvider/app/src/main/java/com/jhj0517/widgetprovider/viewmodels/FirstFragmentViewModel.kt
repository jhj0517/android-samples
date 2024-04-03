package com.jhj0517.widgetprovider.viewmodels

import androidx.lifecycle.ViewModel
import com.jhj0517.widgetprovider.R
import com.jhj0517.widgetprovider.models.ExampleData

class DataViewModel: ViewModel(){
    val exampleDataSet = listOf(
        ExampleData(
            image = R.drawable.strawberry,
            name = "strawberry",
            desc = """
                    The garden strawberry is a widely grown hybrid species of the genus Fragaria,
                    collectively known as the strawberries, which are cultivated worldwide for their fruit.
                    The fruit is widely appreciated for its characteristic aroma, bright red color,
                    juicy texture, and sweetness. It is consumed in large quantities, either fresh or in
                    such prepared foods as jam, juice, pies, ice cream, milkshakes, and chocolates.
                    Artificial strawberry flavorings and aromas are also widely used in products such as
                    candy, soap, lip gloss, perfume, and many others.
                    """.trimIndent()
        ),
        ExampleData(
            image = R.drawable.grapes,
            name = "grapes",
            desc = """
                    A grape is a fruit, botanically a berry, of the deciduous woody vines of the flowering plant genus Vitis.
                    Grapes are a non-climacteric type of fruit, generally occurring in clusters.
                    The cultivation of grapes began perhaps 8,000 years ago, and the fruit has been used as human food over history.
                    Eaten fresh or in dried form (as raisins, currants and sultanas), grapes also hold cultural significance in many parts of the world,
                    particularly for their role in winemaking. Other grape-derived products include various types of jam, juice, vinegar and oil.
                    """.trimIndent()
        )
    )
}