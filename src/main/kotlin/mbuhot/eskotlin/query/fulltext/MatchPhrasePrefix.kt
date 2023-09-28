/*
 * Copyright (c) 2016. Ryan Murfitt rmurfitt@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import org.opensearch.index.query.MatchPhrasePrefixQueryBuilder


/**
 * match_phrase_prefix
 */

class MatchPhrasePrefixBlock {

    @Deprecated(message = "Use invoke operator instead.", replaceWith = ReplaceWith("invoke(init)"))
    infix fun String.to(init: MatchPhrasePrefixData.() -> Unit) = this.invoke(init)

    operator fun String.invoke(init: MatchPhrasePrefixData.() -> Unit) = MatchPhrasePrefixData(name = this).apply(init)

    infix fun String.to(query: Any) = MatchPhrasePrefixData(name = this, query = query)

    data class MatchPhrasePrefixData(
            var name: String,
            var query: Any? = null,
            var analyzer: String? = null,
            var slop: Int? = null,
            var max_expansions: Int? = null)
}

fun match_phrase_prefix(init: MatchPhrasePrefixBlock.() -> MatchPhrasePrefixBlock.MatchPhrasePrefixData): MatchPhrasePrefixQueryBuilder {
    val params = MatchPhrasePrefixBlock().init()
    return MatchPhrasePrefixQueryBuilder(params.name, params.query).apply {
        params.analyzer?.let { analyzer(it) }
        params.slop?.let { slop(it) }
        params.max_expansions?.let { maxExpansions(it) }
    }
}
