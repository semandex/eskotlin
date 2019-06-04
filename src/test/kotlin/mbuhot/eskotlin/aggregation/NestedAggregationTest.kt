package mbuhot.eskotlin.aggregation

import mbuhot.eskotlin.query.compound.bool
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.exists
import mbuhot.eskotlin.query.term.term
import org.junit.Test

class NestedAggregationTest {
    @Test
    fun `nested_aggregation should`() {
        val agg = nested_aggregation {
            name = "nested_aggregation"
            path = "path"
            sub_aggregation = listOf(
                filterAggregation {
                    name = "expiry"
                    filter = bool {
                        must_not { exists { field = "expiryDate" } }
                    }
                },
                filterAggregation {
                    name = "status"
                    filter = term {
                        "user" to "Kimchy"
                    }
                }
            )
        }

        agg should_render_as """
        {
          "nested_aggregation": {
            "nested": {
              "path": "path"
            },
            "aggregations": {
              "expiry": {
                "filter": {
                  "bool": {
                    "must_not": [
                      {
                        "exists": {
                          "field": "expiryDate",
                          "boost": 1.0
                        }
                      }
                    ],
                    "adjust_pure_negative": true,
                    "boost": 1.0
                  }
                }
              },
              "status": {
                "filter": {
                  "term": {
                    "user": {
                        "value": "Kimchy",
                        "boost": 1.0
                    }
                  }
                }
              }
            }
          }
        }
        """.trimIndent()
    }
}