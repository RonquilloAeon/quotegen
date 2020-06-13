package org.ronquilloaeon.domain

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import kotlin.random.Random

class QuoteRepository {
    private val fileName = "quotes.json"

    // See https://medium.com/@hissain.khan/parsing-with-google-gson-library-in-android-kotlin-7920e26f5520
    private fun loadFromFile() : List<Quote>{
        val fileContents = File(fileName).readText()
        val gson = Gson()
        val sType = object : TypeToken<List<Quote>>() { }.type
        return gson.fromJson<List<Quote>>(fileContents, sType)
    }

    fun getRandom() : Quote {
        val quotes = loadFromFile()
        return quotes[Random.nextInt(quotes.size - 1)]
    }
}
