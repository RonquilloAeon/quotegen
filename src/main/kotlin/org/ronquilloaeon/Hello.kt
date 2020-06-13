package org.ronquilloaeon

import org.ronquilloaeon.domain.QuoteRepository
import org.ronquilloaeon.domain.QuoteDBPersistence

fun main(args: Array<String>) {
    // Set up db
    val database = System.getenv("DB_NAME")
    val username = System.getenv("DB_USER")
    val password = System.getenv("DB_PASS")

    val persistence = QuoteDBPersistence(database, username, password)
    val quoteRepository = QuoteRepository(persistence)
    val quotes = quoteRepository.list()

    println("All quotes:")
    quotes.forEach {
        it.print()
        println()
    }

    var doContinue = true

    while (doContinue) {
        print("Get a random quote? (y/n) ")
        doContinue = readLine().toString() == "y"

        quoteRepository.getRandom().print()
        println()
    }

    persistence.close()
}
