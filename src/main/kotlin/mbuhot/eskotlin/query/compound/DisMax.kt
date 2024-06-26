/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.opensearch.index.query.DisMaxQueryBuilder
import org.opensearch.index.query.QueryBuilder

data class DisMaxData(
    var tie_breaker: Float? = null,
    var boost: Float? = null,
    var queries: List<QueryBuilder>? = null)

fun dis_max(init: DisMaxData.() -> Unit): DisMaxQueryBuilder {
    val params = DisMaxData().apply(init)
    return DisMaxQueryBuilder().apply {
        params.tie_breaker?.let { tieBreaker(it) }
        params.boost?.let { boost(it) }
        params.queries?.forEach { add(it) }
    }
}
