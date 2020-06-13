package org.ronquilloaeon.domain

import com.github.jasync.sql.db.Configuration
import com.github.jasync.sql.db.Connection
import com.github.jasync.sql.db.general.ArrayRowData
import com.github.jasync.sql.db.postgresql.pool.PostgreSQLConnectionFactory

interface QuotePersistence {
    fun list() : List<Quote>
    fun random() : Quote
    fun close()
}

// See https://github.com/jasync-sql/jasync-postgresql-example/blob/master/src/main/kotlin/Main.kt
class QuoteDBPersistence(
    private val database: String, private val username: String, private val password: String
) : QuotePersistence {
    private val connection: Connection

    init {
        this.connection = PostgreSQLConnectionFactory(
            Configuration(username=username, password=password, database=database)
        ).create().get()
    }

    override fun list() : List<Quote> {
        val future = connection.sendPreparedStatement("select id, passage, author from quote")
        val queryResult = future.get()

        return queryResult.rows.map { it as ArrayRowData
            Quote.init(it.columns[0].toString().toInt(), it.columns[1].toString(), it.columns[2].toString())
        }
    }

    override fun random(): Quote {
        val future = connection.sendPreparedStatement("select id, passage, author from quote order by random() limit 1")
        val queryResult = future.get()
        val data = (queryResult.rows[0] as ArrayRowData)

        return Quote.init(data.columns[0].toString().toInt(), data.columns[1].toString(), data.columns[2].toString())
    }

    override fun close() {
        this.connection.disconnect().get()
    }
}

class QuoteRepository(val persistence: QuotePersistence) {
    fun getRandom() : Quote {
        return persistence.random()
    }

    fun list() : List<Quote> {
        return persistence.list()
    }
}
