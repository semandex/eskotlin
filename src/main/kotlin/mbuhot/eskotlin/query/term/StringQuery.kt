/*
 * Copyright (c) 2021. Ehsan Souri  ehsansouri23@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.QueryData
import mbuhot.eskotlin.query.initQuery
import org.elasticsearch.index.query.Operator
import org.elasticsearch.index.query.QueryStringQueryBuilder

class StringBlock {
    class StringData(
        var searchText: String,
        var field: String,
        var defaultOperator: Operator = Operator.AND
    ) : QueryData()

    infix fun String.startsWith(searchText: String): StringData = StringData("$searchText*", this)
    infix fun String.endsWith(searchText: String): StringData = StringData("*$searchText", this)
    infix fun String.equalsTo(searchText: String): StringData = StringData(searchText, this)
    infix fun String.contains(searchText: String): StringData = StringData("*$searchText*", this)
}

fun string(init: StringBlock.() -> StringBlock.StringData): QueryStringQueryBuilder {
    val params = StringBlock().init()
    return QueryStringQueryBuilder(params.searchText).field(params.field).analyzeWildcard(true)
        .apply {
            defaultOperator(params.defaultOperator)
            initQuery(params)
        }
}