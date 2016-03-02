/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class IndicesTest {

    @Test
    fun `test indices`() {

        val query = indices {
            indices = listOf("index1", "index2")
            query {
                term { "tag" to "wow" }
            }
            no_match_query {
                term { "tag" to "kow" }
            }
        }

        query should_render_as """
        {
            "indices" : {
                "indices" : ["index1", "index2"],
                "query" : {
                    "term" : { "tag" : "wow" }
                },
                "no_match_query" : {
                    "term" : { "tag" : "kow" }
                }
            }
        }
        """
    }

    @Test
    fun `test indices with string no_match_query`() {
        val query = indices {
            indices = listOf("index1", "index2")
            query {
                term { "tag" to "wow" }
            }
            no_match_query = "none"
        }

        query should_render_as """
        {
            "indices" : {
                "indices" : ["index1", "index2"],
                "query" : {
                    "term" : { "tag" : "wow" }
                },
                "no_match_query" : "none"
            }
        }
        """
    }
}