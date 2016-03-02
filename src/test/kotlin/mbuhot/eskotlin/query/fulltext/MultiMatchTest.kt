/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import mbuhot.eskotlin.query.should_render_as
import org.elasticsearch.index.query.MatchQueryBuilder
import org.elasticsearch.index.query.MultiMatchQueryBuilder
import org.junit.Test


class MultiMatchTest {

    @Test
    fun `test multi_match`() {
        val query = multi_match {
            query = "this is a test"
            fields = listOf("subject", "message")
        }
        query should_render_as """
            {
              "multi_match" : {
                "query":    "this is a test",
                "fields": [ "subject", "message" ]
              }
            }
            """
    }

    @Test
    fun `test multi_match with best_fields type`() {
        val query = multi_match {
            query = "brown fox"
            type = MultiMatchQueryBuilder.Type.BEST_FIELDS
            fields = listOf("subject", "message")
            tie_breaker = 0.3f
        }
        query should_render_as """
            {
              "multi_match" : {
                "query":      "brown fox",
                "fields":     [ "subject", "message" ],
                "type":       "best_fields",
                "tie_breaker": 0.3
              }
            }
            """
    }

    @Test
    fun `test multi_match with operator`() {
        val query = multi_match {
            query = "Will Smith"
            type = MultiMatchQueryBuilder.Type.CROSS_FIELDS
            fields = listOf("first_name", "last_name")
            operator = MatchQueryBuilder.Operator.AND
        }

        query should_render_as """
            {
              "multi_match" : {
                "query":      "Will Smith",
                "fields":     [ "first_name", "last_name" ],
                "type":       "cross_fields",
                "operator":   "AND"
              }
            }
            """
    }

    @Test
    fun `test multi_match with analyzer`() {
        val query = multi_match {
            query = "Jon"
            type = MultiMatchQueryBuilder.Type.CROSS_FIELDS
            analyzer = "standard"
            fields = listOf("first", "last", "*.edge")
        }

        query should_render_as """
            {
              "multi_match" : {
                "query":      "Jon",
                "fields":     [ "first", "last", "*.edge" ],
                "type":       "cross_fields",
                "analyzer":   "standard"
              }
            }
            """
    }
}