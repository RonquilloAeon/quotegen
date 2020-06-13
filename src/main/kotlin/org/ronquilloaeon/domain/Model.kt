package org.ronquilloaeon.domain

class Quote(val id: Int, val passage: String, val author: String) {
    fun print() {
        println("[$id] $author said:\n$passage")
    }

    companion object {
        fun init(id: Int?, passage: String, author: String) : Quote {
            return Quote(id ?: -1, passage, author)
        }
    }
}
