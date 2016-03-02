/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.MatchQueryBuilder
import org.elasticsearch.index.query.MatchQueryBuilder.Type.PHRASE
import org.elasticsearch.index.query.MatchQueryBuilder.Type.PHRASE_PREFIX


/**
 * match
 */

class MatchBlock {
    infix fun String.to(init: MatchData.() -> Unit) = MatchData(name = this).apply(init)
    infix fun String.to(query: Any) = MatchData(name = this, query = query)

    data class MatchData(
        var name: String,
        var query: Any? = null,
        var type: MatchQueryBuilder.Type? = null,
        var operator: MatchQueryBuilder.Operator? = null,
        var analyzer: String? = null,
        var boost: Float? = null,
        var slop: Int? = null,
        var fuzziness: Fuzziness? = null,
        var prefix_length: Int? = null,
        var max_expansions: Int? = null,
        var minimum_should_match: String? = null,
        var fuzzy_rewrite: String? = null,
        var lenient: Boolean? = null,
        var fuzzy_transpositions: Boolean? = null,
        var zero_terms_query: MatchQueryBuilder.ZeroTermsQuery? = null,
        var cutoff_frequency: Float? = null)
}

fun match(init: MatchBlock.() -> MatchBlock.MatchData): MatchQueryBuilder {
    val params = MatchBlock().init()
    return MatchQueryBuilder(params.name, params.query).apply {
        params.analyzer?.let { analyzer(it) }
        params.boost?.let { boost(it) }
        params.cutoff_frequency?.let { cutoffFrequency(it) }
        params.fuzziness?.let { fuzziness(it) }
        params.fuzzy_rewrite?.let { fuzzyRewrite(it) }
        params.fuzzy_transpositions?.let { fuzzyTranspositions(it) }
        params.lenient?.let { setLenient(it) }
        params.max_expansions?.let { maxExpansions(it) }
        params.minimum_should_match?.let { minimumShouldMatch(it) }
        params.operator?.let { operator(it) }
        params.prefix_length?.let { prefixLength(it) }
        params.slop?.let { slop(it) }
        params.type?.let { type(it) }
        params.zero_terms_query?.let { zeroTermsQuery(it) }
    }
}


fun match_phrase(init: MatchBlock.() -> MatchBlock.MatchData) =
    match {
        init().apply { type = PHRASE }
    }

fun match_phrase_prefix(init: MatchBlock.() -> MatchBlock.MatchData) =
    match {
        init().apply { type = PHRASE_PREFIX }
    }
