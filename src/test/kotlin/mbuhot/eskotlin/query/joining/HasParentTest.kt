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
class HasParentTest {

    @Test
    fun `test has_parent`() {

        val query = has_parent {
            parent_type = "blog"
            score_mode = "score"
            query {
                term {
                    "tag" to "something"
                }
            }
            inner_hits = QueryInnerHitBuilder()
        }

        query should_render_as """
        {
            "has_parent" : {
                "query" : {
                    "term" : {
                        "tag" : "something"
                    }
                },
                "parent_type" : "blog",
                "score_mode" : "score",
                "inner_hits" : {}
            }
        }
        """
    }
}