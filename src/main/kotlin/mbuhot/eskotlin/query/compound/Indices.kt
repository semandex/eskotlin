/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.IndicesQueryBuilder
import org.elasticsearch.index.query.QueryBuilder

data class IndicesData(
    var indices: List<String> = emptyList(),
    var query: QueryBuilder? = null,
    var no_match_query: String? = null,
    var no_match_query_builder: QueryBuilder? = null) {

    fun query(f: () -> QueryBuilder) {
        query = f()
    }

    fun no_match_query(f: () -> QueryBuilder) {
        no_match_query_builder = f()
    }
}


fun indices(init: IndicesData.() -> Unit): IndicesQueryBuilder {
    val params = IndicesData().apply(init)
    return IndicesQueryBuilder(params.query, *params.indices.toTypedArray()).apply {
        params.no_match_query?.let { noMatchQuery(it) }
        params.no_match_query_builder?.let { noMatchQuery(it) }
    }
}

