/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.PrefixQueryBuilder

class PrefixBlock {
    class PrefixData(
            val name: String,
            var prefix: String? = null) : QueryData()

    infix fun String.to(prefix: String) = PrefixData(name = this, prefix = prefix)

    @Deprecated(message = "Use invoke operator instead.", replaceWith = ReplaceWith("invoke(init)"))
    infix fun String.to(init: PrefixData.() -> Unit) = this.invoke(init)

    operator fun String.invoke(init: PrefixData.() -> Unit) = PrefixData(name = this).apply(init)
}

fun prefix(init: PrefixBlock.() -> PrefixBlock.PrefixData): PrefixQueryBuilder {
    val params = PrefixBlock().init()
    return PrefixQueryBuilder(params.name, params.prefix).apply {
        initQuery(params)
    }
}