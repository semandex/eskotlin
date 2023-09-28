/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.joining

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.opensearch.index.query.InnerHitBuilder
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
            query {
                term {
                    "tag" to "something"
                }
            }
            inner_hits = InnerHitBuilder()
        }

        query should_render_as """
        {
            "has_parent": {
                "query": {
                    "term": {
                        "tag": {
                            "value": "something",
                            "boost": 1.0
                        }
                    }
                },
                "parent_type": "blog",
                "score": false,
                "ignore_unmapped": false,
                "boost": 1.0,
                "inner_hits": {
                    "ignore_unmapped": false,
                    "from": 0,
                    "size": 3,
                    "version": false,
                    "seq_no_primary_term": false,
                    "explain": false,
                    "track_scores": false
                }
            }
        }
        """
    }
}