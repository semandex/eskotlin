/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.elasticsearch.index.query.support.QueryInnerHitBuilder
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class HasChildTest {

    @Test
    fun `test has_child`() {
        val query = has_child {
            type = "blog_tag"
            score_mode = "sum"
            min_children = 2
            max_children = 10
            query {
                term {
                    "tag" to "something"
                }
            }
            inner_hits = QueryInnerHitBuilder()
        }
        query should_render_as """
        {
            "has_child" : {
                "query" : {
                    "term" : {
                        "tag" : "something"
                    }
                },
                "child_type" : "blog_tag",
                "score_mode" : "sum",
                "min_children": 2,
                "max_children": 10,
                "inner_hits" : {}
            }
        }
        """
    }
}