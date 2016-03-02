/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder

data class FunctionScoreData(
    var query: QueryBuilder? = null,
    var boost: Float? = null,
    var functions: List<Pair<QueryBuilder?, ScoreFunctionBuilder>>? = null,
    var max_boost: Float? = null,
    var score_mode: String? = null,
    var boost_mode: String? = null,
    var min_score: Float? = null)

fun function_score(init: FunctionScoreData.() -> Unit): FunctionScoreQueryBuilder {
    val params = FunctionScoreData().apply(init)
    val builder = if (params.query is QueryBuilder) {
        FunctionScoreQueryBuilder(params.query)
    } else {
        FunctionScoreQueryBuilder()
    }

    return builder.apply {
        params.boost?.let { boost(it) }
        params.max_boost?.let { maxBoost(it) }
        params.score_mode?.let { scoreMode(it) }
        params.boost_mode?.let { boostMode(it) }
        params.min_score?.let { setMinScore(it) }
        params.functions?.forEach { add(it.first, it.second) }
    }
}
