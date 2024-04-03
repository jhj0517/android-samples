package com.jhj0517.custom_tooltip_window.viewmoels

import androidx.lifecycle.ViewModel
import com.jhj0517.custom_tooltip_window.R
import com.jhj0517.custom_tooltip_window.models.ExampleData


class FirstFragmentViewModel: ViewModel(){
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
    )
}