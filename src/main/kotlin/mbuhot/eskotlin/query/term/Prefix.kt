/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.PrefixQueryBuilder

class PrefixBlock {
    data class PrefixData(
        val name: String,
        var prefix: String? = null,
        var boost: Float? = null)

    infix fun String.to(prefix: String) = PrefixData(name = this, prefix = prefix)

    infix fun String.to(init: PrefixData.() -> Unit): PrefixData =
        PrefixData(name = this).apply(init)
}

fun prefix(init: PrefixBlock.() -> PrefixBlock.PrefixData): PrefixQueryBuilder {
    val params = PrefixBlock().init()
    return PrefixQueryBuilder(params.name, params.prefix).apply {
        params.boost?.let { boost(it) }
    }
}