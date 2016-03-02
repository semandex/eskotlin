/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import org.elasticsearch.index.query.NestedQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.support.QueryInnerHitBuilder


data class NestedData(
    var query: QueryBuilder? = null,
    var path: String? = null,
    var score_mode: String? = null,
    var boost: Float? = null,
    var inner_hits: QueryInnerHitBuilder? = null) {

    fun query(f: () -> QueryBuilder) {
        query = f()
    }
}

fun nested(init: NestedData.() -> Unit): NestedQueryBuilder {
    val params = NestedData().apply(init)
    return NestedQueryBuilder(params.path, params.query).apply {
        params.score_mode?.let { scoreMode(it) }
        params.boost?.let { boost(it) }
        params.inner_hits?.let { innerHit(it) }
    }
}