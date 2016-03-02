/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.boost
import mbuhot.eskotlin.query.term.match_all
import org.elasticsearch.index.query.MatchQueryBuilder
import org.junit.Test

/**
 * Created on 2/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */


class MatchTest {
    @Test
    fun `test match_all`() {
        val query = match_all {
            boost = 1.2f
        }
        query should_render_as """{ "match_all": { "boost" : 1.2 }}"""
    }

    @Test
    fun `test simple match`() {
        val query = match { "message" to "this is a test" }
        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "this is a test"
                    }
                }
            }
            """
    }

    @Test
    fun `test match with query and operator`() {


        val query = match {
            "message" to {
                query = "this is a test"
                operator = MatchQueryBuilder.Operator.AND
            }
        }

        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "this is a test",
                        "operator" : "AND"
                    }
                }
            }
            """
    }

    @Test
    fun `test match zero terms query`() {

        val query = match {
            "message" to {
                query = "to be or not to be"
                operator = MatchQueryBuilder.Operator.AND
                zero_terms_query = MatchQueryBuilder.ZeroTermsQuery.ALL
            }
        }
        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "to be or not to be",
                        "operator" : "AND",
                        "zero_terms_query": "ALL"
                    }
                }
            }
            """
    }


    @Test
    fun `test match cutoff frequency`() {
        val query = match {
            "message" to {
                query = "to be or not to be"
                cutoff_frequency = 0.001f
            }
        }

        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "to be or not to be",
                        "cutoff_frequency" : 0.001
                    }
                }
            }
            """
    }

    @Test
    fun `test match type phrase`() {
        val query = match {
            "message" to {
                query = "this is a test"
                type = MatchQueryBuilder.Type.PHRASE
            }
        }

        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "this is a test",
                        "type" : "phrase"
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase`() {
        val query = match_phrase {
            "message" to "this is a test"
        }

        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "this is a test",
                        "type" : "phrase"
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase with analyzer`() {
        val query = match_phrase {
            "message" to {
                query = "this is a test"
                analyzer = "my_analyzer"
            }
        }
        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "this is a test",
                        "type" : "phrase",
                        "analyzer" : "my_analyzer"
                    }
                }
            }
            """
    }

    @Test
    fun `test match_phrase_prefix`() {
        val query = match_phrase_prefix {
            "message" to "this is a test"
        }

        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "this is a test",
                        "type" : "phrase_prefix"
                    }
                }
            }
            """
    }

    @Test
    fun `test match with type phrase_prefix`() {
        val query = match {
            "message" to {
                query = "this is a test"
                type = MatchQueryBuilder.Type.PHRASE_PREFIX
            }
        }

        query should_render_as """
        {
            "match" : {
                "message" : {
                    "query" : "this is a test",
                    "type" : "phrase_prefix"
                }
            }
        }
        """
    }

    @Test
    fun `test match_phrase_prefix with max_expansions`() {
        val query = match_phrase_prefix {
            "message" to {
                query = "this is a test"
                max_expansions = 10
            }
        }

        query should_render_as """
            {
                "match" : {
                    "message" : {
                        "query" : "this is a test",
                        "type" : "phrase_prefix",
                        "max_expansions" : 10
                    }
                }
            }
            """
    }
}