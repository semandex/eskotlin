/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.IdsQueryBuilder

data class IdsData(
    var types: List<String> = emptyList(),
    var values: List<String>? = null) {

    var type: String
        get() = types[0]
        set(value) {
            types = listOf(value)
        }
}


fun ids(init: IdsData.() -> Unit): IdsQueryBuilder {
    val params = IdsData().apply(init)
    return IdsQueryBuilder(*params.types.toTypedArray()).apply {
        addIds(params.values)
    }
}