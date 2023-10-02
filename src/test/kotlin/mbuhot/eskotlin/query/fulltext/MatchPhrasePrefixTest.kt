/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import mbuhot.eskotlin.query.should_render_as
import org.junit.jupiter.api.Test

/**
 * Created on 2/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */


class MatchPhrasePrefixTest {

    @Test
    fun `test match_phrase_prefix`() {
        val query = match_phrase_prefix {
            "message" to "this is a test"
        }

        query should_render_as """
            {
                "match_phrase_prefix" : {
                    "message" : {
                        "query" : "this is a test",
                        "slop":0,
                        "max_expansions":50,
                        "zero_terms_query":"NONE",
                        "boost":1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase_prefix with max_expansions`() {
        val query = match_phrase_prefix {
            "message" {
                query = "this is a test"
                max_expansions = 10
            }
        }

        query should_render_as """
            {
                "match_phrase_prefix" : {
                    "message" : {
                        "query" : "this is a test",
                        "slop":0,
                        "max_expansions":10,
                        "zero_terms_query":"NONE",
                        "boost":1.0
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase_prefix with max_expansions and slop`() {
        val query = match_phrase_prefix {
            "message" {
                query = "this is a test"
                max_expansions = 10
                slop = 2
            }
        }

        query should_render_as """
            {
                "match_phrase_prefix" : {
                    "message" : {
                        "query" : "this is a test",
                        "slop":2,
                        "max_expansions":10,
                        "zero_terms_query":"NONE",
                        "boost":1.0
                    }
                }
            }
            """
    }
}