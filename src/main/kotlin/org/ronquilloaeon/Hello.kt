package org.ronquilloaeon

import org.ronquilloaeon.domain.QuoteRepository

fun main(args: Array<String>) {
    val quote = QuoteRepository().getRandom()
    val author = if (quote.author.isBlank()) "unknown" else quote.author

    println("Your random quote is by $author:\n${quote.text}")
}
