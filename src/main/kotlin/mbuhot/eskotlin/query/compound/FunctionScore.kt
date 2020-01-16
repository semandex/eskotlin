/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import org.elasticsearch.common.lucene.search.function.CombineFunction
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder
import org.elasticsearch.index.query.MatchAllQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders

data class FunctionScoreData(
        var query: QueryBuilder = MatchAllQueryBuilder(),
        var boost: Float? = null,
        var functions: List<Pair<QueryBuilder?, ScoreFunctionBuilder<*>>> = emptyList(),
        var field_value_factor: FieldValueFactorFunctionBuilder? = null,
        var max_boost: Float? = null,
        var score_mode: String? = null,
        var boost_mode: String? = null,
        var min_score: Float? = null)

fun function_score(init: FunctionScoreData.() -> Unit): FunctionScoreQueryBuilder {
    val params = FunctionScoreData().apply(init)
    val factorWrapper = listOf(Pair(null as QueryBuilder?, params.field_value_factor as ScoreFunctionBuilder<*>?))
    val merged  = factorWrapper + params.functions;

    val filterFunctions = merged.filter { it.second != null }.map {
        if (it.first == null) {
            FunctionScoreQueryBuilder.FilterFunctionBuilder(it.second)
        } else {
            FunctionScoreQueryBuilder.FilterFunctionBuilder(it.first, it.second)
        }
    }

    val builder = FunctionScoreQueryBuilder(params.query, filterFunctions.toTypedArray())

    return builder.apply {
        params.boost?.let { boost(it) }
        params.max_boost?.let { maxBoost(it) }
        params.score_mode?.let { scoreMode(FunctionScoreQuery.ScoreMode.fromString(it)) }
        params.boost_mode?.let { boostMode(CombineFunction.fromString(it)) }
        params.min_score?.let { setMinScore(it) }
    }
}
