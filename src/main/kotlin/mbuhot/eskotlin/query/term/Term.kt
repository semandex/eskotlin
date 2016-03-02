/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.TermQueryBuilder

class TermBlock {
    data class TermData(
        var name: String? = null,
        var value: String? = null,
        var boost: Float? = null)

    infix fun String.to(value: String): TermData {
        return TermData(name = this, value = value)
    }

    infix fun String.to(init: TermData.() -> Unit): TermData {
        return TermData(name = this).apply(init)
    }
}

fun term(init: TermBlock.() -> TermBlock.TermData): TermQueryBuilder {
    val params = TermBlock().init()
    return TermQueryBuilder(params.name, params.value).apply {
        with (params) {
            boost?.let { boost(it) }
        }
    }
}