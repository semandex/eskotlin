/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.TermsQueryBuilder

class TermsBlock {
    data class TermsData(
        var name: String,
        var values: List<Any>)

    infix fun String.to(values: List<Any>): TermsData {
        return TermsData(name = this, values = values)
    }
}

fun terms(init: TermsBlock.() -> TermsBlock.TermsData): TermsQueryBuilder {
    val params = TermsBlock().init()
    return TermsQueryBuilder(params.name, *params.values.toTypedArray())
}