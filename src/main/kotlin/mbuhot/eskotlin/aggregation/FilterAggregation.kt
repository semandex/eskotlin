package mbuhot.eskotlin.aggregation

import org.opensearch.index.query.QueryBuilder
import org.opensearch.search.aggregations.AggregationBuilder
import org.opensearch.search.aggregations.bucket.filter.FilterAggregationBuilder

/**
 */

data class FilterAggregationData(
    var name: String? = null,
    var filter: QueryBuilder? = null
)

fun filterAggregation(init: FilterAggregationData.() -> Unit): AggregationBuilder {
    val params = FilterAggregationData().apply(init)
    return FilterAggregationBuilder(
        params.name,
        params.filter
    )
}