/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.BoostingQueryBuilder
import org.elasticsearch.index.query.QueryBuilder

data class BoostingData(
    var positive: QueryBuilder? = null,
    var negative: QueryBuilder? = null,
    var boost: Float? = null,
    var negative_boost: Float? = null) {

    fun positive(f: () -> QueryBuilder) {
        positive = f()
    }

    fun negative(f: () -> QueryBuilder) {
        negative = f()
    }
}

fun boosting(init: BoostingData.() -> Unit): BoostingQueryBuilder {
    val params = BoostingData().apply(init)
    return BoostingQueryBuilder().apply {
        params.positive?.let { positive(it) }
        params.negative?.let { negative(it) }
        params.boost?.let { boost(it) }
        params.negative_boost?.let { negativeBoost(it) }
    }
}
