/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.ExistsQueryBuilder

data class ExistsData(var field: String? = null)

fun exists(init: ExistsData.() -> Unit): ExistsQueryBuilder {
    val params = ExistsData().apply(init)
    return ExistsQueryBuilder(params.field)
}
