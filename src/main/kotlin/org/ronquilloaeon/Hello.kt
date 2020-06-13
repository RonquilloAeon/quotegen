package org.ronquilloaeon

import org.ronquilloaeon.domain.QuoteRepository

fun main(args: Array<String>) {
    println("Your random quote is '${QuoteRepository().getRandom()}'")
}
