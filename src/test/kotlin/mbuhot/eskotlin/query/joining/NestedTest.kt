/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import mbuhot.eskotlin.query.compound.bool
import mbuhot.eskotlin.query.fulltext.match
import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.range
import org.elasticsearch.index.query.support.QueryInnerHitBuilder
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class NestedTest {

    @Test
    fun `test nested`() {
        val query = nested {
            path = "obj1"
            score_mode = "avg"
            query {
                bool {
                    must = listOf(
                        match { "obj1.name" to "blue" },
                        range { "obj1.count" to { gt = 5 } }
                    )
                }
            }
            inner_hits = QueryInnerHitBuilder()
        }

        query should_render_as """
        {
            "nested" : {
                "query" : {
                    "bool" : {
                        "must" : [
                            {
                                "match" : {"obj1.name" : { "query": "blue"} }
                            },
                            {
                                "range" : {
                                    "obj1.count" : {
                                        "from" : 5,
                                        "to" : null,
                                        "include_lower" : false,
                                        "include_upper" : true
                                    }
                                }
                            }
                        ]
                    }
                },
                "path" : "obj1",
                "score_mode" : "avg",
                "inner_hits" : {}
            }
        }
        """
    }
}