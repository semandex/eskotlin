/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import org.elasticsearch.index.query.HasParentQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.support.QueryInnerHitBuilder

data class HasParentData(
    var query: QueryBuilder? = null,
    var parent_type: String? = null,
    var score_mode: String? = null,
    var boost: Float? = null,
    var inner_hits: QueryInnerHitBuilder? = null) {

    fun query(f: () -> QueryBuilder) {
        query = f()
    }
}

fun has_parent(init: HasParentData.() -> Unit): HasParentQueryBuilder {
    val params = HasParentData().apply(init)
    return HasParentQueryBuilder(params.parent_type, params.query).apply {
        params.score_mode?.let { scoreMode(it) }
        params.boost?.let { boost(it) }
        params.inner_hits?.let { innerHit(it) }
    }
}