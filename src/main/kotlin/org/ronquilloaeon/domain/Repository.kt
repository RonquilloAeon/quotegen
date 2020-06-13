package org.ronquilloaeon.domain

import kotlin.random.Random

class QuoteRepository {
    private val quotes = arrayOf(
        "The way to get started is to quit talking and begin doing.",
        "If life were predictable it would cease to be life, and be without flavor.",
        "Life is what happens when you're busy making other plans.",
        "Whoever is happy will make others happy too.",
        "Distraction is the enemy of vision."
    )

    fun getRandom() : String {
        return quotes[Random.nextInt(quotes.size - 1)]
    }
}
