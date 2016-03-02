/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import org.elasticsearch.index.query.HasChildQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.support.QueryInnerHitBuilder

data class HasChildData(
    var query: QueryBuilder? = null,
    var type: String? = null,
    var boost: Float? = null,
    var score_mode: String? = null,
    var min_children: Int? = null,
    var max_children: Int? = null,
    var short_circuit_cutoff: Int? = null,
    var inner_hits: QueryInnerHitBuilder? = null) {

    fun query(f: () -> QueryBuilder) {
        query = f()
    }
}

fun has_child(init: HasChildData.() -> Unit): HasChildQueryBuilder {
    val params = HasChildData().apply(init)
    return HasChildQueryBuilder(params.type, params.query).apply {
        params.boost?.let { boost(it) }
        params.score_mode?.let { scoreMode(it) }
        params.min_children?.let { minChildren(it) }
        params.max_children?.let { maxChildren(it) }
        params.short_circuit_cutoff?.let { setShortCircuitCutoff(it) }
        params.inner_hits?.let { innerHit(it) }
    }
}