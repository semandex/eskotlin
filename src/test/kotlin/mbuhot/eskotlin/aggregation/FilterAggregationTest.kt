package mbuhot.eskotlin.aggregation

import mbuhot.eskotlin.query.compound.bool
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.exists
import org.junit.Test

class FilterAggregationTest {
    @Test
    fun `filter aggregation should`() {
        val agg = filterAggregation {
            name = "expiry"
            filter = bool {
                must_not { exists { field = "expiryDate" } }
            }
        }

        agg should_render_as """
        {
            "expiry" : {
              "filter" : {
                "bool" : {
                  "must_not" : [
                    {
                      "exists" : {
                        "field" : "expiryDate",
                        "boost" : 1.0
                      }
                    }
                  ],
                  "adjust_pure_negative" : true,
                  "boost" : 1.0
                }
              }
            }
        }
        """
    }
}