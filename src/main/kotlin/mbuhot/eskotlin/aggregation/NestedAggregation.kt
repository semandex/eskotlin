package mbuhot.eskotlin.aggregation

import org.opensearch.search.aggregations.AggregationBuilder
import org.opensearch.search.aggregations.bucket.nested.NestedAggregationBuilder

data class NestedAggregationData(
    var name: String? = null,
    var path: String? = null,
    var sub_aggregation: List<AggregationBuilder>? = null) {
    fun sub_aggregation(f: () -> AggregationBuilder) {
        sub_aggregation = listOf(f())
    }
}

fun nested_aggregation(init: NestedAggregationData.() -> Unit): NestedAggregationBuilder {
    val params = NestedAggregationData().apply(init)
    return NestedAggregationBuilder(params.name, params.path).apply {
        params.sub_aggregation?.forEach {
            subAggregation(it)
        }
    }
}