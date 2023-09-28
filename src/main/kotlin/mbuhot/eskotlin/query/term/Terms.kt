/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.opensearch.index.query.TermsQueryBuilder
import org.opensearch.indices.TermsLookup

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

    class TermsLookupData(
            var index: String? = null,
            var type: String? = null,
            var id: String? = null,
            var path: String? = null
    )

    operator fun String.invoke(init: TermsLookupData.() -> Unit): TermsData {
        val lookup = TermsLookupData().run {
            init()
            TermsLookup(index, type, id, path)
        }
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