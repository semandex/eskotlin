/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.RegexpFlag
import org.elasticsearch.index.query.RegexpQueryBuilder

class RegexpBlock {
    data class RegexpData(
        val name: String,
        var value: String? = null,
        var boost: Float? = null,
        var flags: List<RegexpFlag>? = null,
        var max_determinized_states: Int? = null)

    infix fun String.to(value: String) = RegexpData(name = this, value = value)

    infix fun String.to(init: RegexpData.() -> Unit): RegexpData =
        RegexpData(name = this).apply(init)
}

fun regexp(init: RegexpBlock.() -> RegexpBlock.RegexpData): RegexpQueryBuilder {
    val params = RegexpBlock().init()
    return RegexpQueryBuilder(params.name, params.value).apply {
        params.boost?.let { boost(it) }
        params.flags?.let { flags(*it.toTypedArray()) }
        params.max_determinized_states?.let { maxDeterminizedStates(it) }
    }
}

