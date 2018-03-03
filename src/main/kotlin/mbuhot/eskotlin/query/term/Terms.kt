/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.TermsQueryBuilder
import org.elasticsearch.indices.TermsLookup

class TermsBlock {
    class TermsData(
            var name: String,
            var values: List<Any> = emptyList(),
            var termsLookup: TermsLookup? = null) : QueryData()

    infix fun String.to(values: List<Any>): TermsData {
        return TermsData(name = this, values = values)
    }

    infix fun String.to(lookup: TermsLookup): TermsData {
        return TermsData(name = this, termsLookup = lookup)
    }
}

fun terms(init: TermsBlock.() -> TermsBlock.TermsData): TermsQueryBuilder {
    val params = TermsBlock().init()
    return when {
        params.termsLookup is TermsLookup ->
            TermsQueryBuilder(params.name, params.termsLookup)

        else ->
            TermsQueryBuilder(params.name, params.values)
    }
    .apply {
        initQuery(params)
    }
}