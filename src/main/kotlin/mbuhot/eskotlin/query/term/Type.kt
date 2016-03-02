/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import org.elasticsearch.index.query.TypeQueryBuilder

data class TypeData(var value: String? = null)

fun type(init: TypeData.() -> Unit): TypeQueryBuilder =
    TypeQueryBuilder(TypeData().apply(init).value)
