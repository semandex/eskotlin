/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.FuzzyQueryBuilder

class FuzzyBlock {
    data class FuzzyData(
        val name: String,
        var value: Any? = null,
        var boost: Float? = null,
        var fuzziness: Fuzziness? = null,
        var prefix_length: Int? = null,
        var max_expansions: Int? = null)

    infix fun String.to(value: Any) = FuzzyData(name = this, value = value)

    infix fun String.to(init: FuzzyData.() -> Unit): FuzzyData =
        FuzzyData(name = this).apply(init)
}

fun fuzzy(init: FuzzyBlock.() -> FuzzyBlock.FuzzyData): FuzzyQueryBuilder {
    val params = FuzzyBlock().init()
    return FuzzyQueryBuilder(params.name, params.value).apply {
        params.boost?.let { boost(it) }
        params.fuzziness?.let { fuzziness(it) }
        params.prefix_length?.let { prefixLength(it) }
        params.max_expansions?.let { maxExpansions(it) }
    }
}

