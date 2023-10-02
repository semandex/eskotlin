/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.fulltext

import mbuhot.eskotlin.query.should_render_as
import org.junit.jupiter.api.Test


class MultiMatchTest {

    @Test
    fun `test multi_match`() {
        val query = multi_match {
            query = "this is a test"
            fields = listOf("subject", "message")
        }
        query should_render_as """
            {
                "multi_match": {
                    "query": "this is a test",
                    "fields": ["message^1.0", "subject^1.0"],
                    "type": "best_fields",
                    "operator": "OR",
                    "slop": 0,
                    "prefix_length": 0,
                    "max_expansions": 50,
                    "zero_terms_query": "NONE",
                    "auto_generate_synonyms_phrase_query": true,
                    "fuzzy_transpositions": true,
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test multi_match with best_fields type`() {
        val query = multi_match {
            query = "brown fox"
            type = "best_fields"
            fields = listOf("subject", "message")
            tie_breaker = 0.3f
        }
        query should_render_as """
            {
                "multi_match": {
                    "query": "brown fox",
                    "fields": ["message^1.0", "subject^1.0"],
                    "type": "best_fields",
                    "operator": "OR",
                    "slop": 0,
                    "prefix_length": 0,
                    "max_expansions": 50,
                    "tie_breaker": 0.3,
                    "zero_terms_query": "NONE",
                    "auto_generate_synonyms_phrase_query": true,
                    "fuzzy_transpositions": true,
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test multi_match with operator`() {
        val query = multi_match {
            query = "Will Smith"
            type = "cross_fields"
            fields = listOf("first_name", "last_name")
            operator = "and"
        }

        query should_render_as """
            {
                "multi_match": {
                    "query": "Will Smith",
                    "fields": ["first_name^1.0", "last_name^1.0"],
                    "type": "cross_fields",
                    "operator": "AND",
                    "slop": 0,
                    "prefix_length": 0,
                    "max_expansions": 50,
                    "zero_terms_query": "NONE",
                    "auto_generate_synonyms_phrase_query": true,
                    "fuzzy_transpositions": true,
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test multi_match with analyzer`() {
        val query = multi_match {
            query = "Jon"
            type = "cross_fields"
            analyzer = "standard"
            fields = listOf("first", "last", "*.edge")
        }

        query should_render_as """
            {
                "multi_match": {
                    "query": "Jon",
                    "fields": ["*.edge^1.0", "first^1.0", "last^1.0"],
                    "type": "cross_fields",
                    "operator": "OR",
                    "analyzer": "standard",
                    "slop": 0,
                    "prefix_length": 0,
                    "max_expansions": 50,
                    "zero_terms_query": "NONE",
                    "auto_generate_synonyms_phrase_query": true,
                    "fuzzy_transpositions": true,
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test multi_match with boost`() {
        val query = multi_match {
            query = "this is a test"
            fields = listOf("subject","message^2.0")
        }
        query should_render_as """
            {
                "multi_match": {
                    "query": "this is a test",
                    "fields": ["message^2.0", "subject^1.0"],
                    "type": "best_fields",
                    "operator": "OR",
                    "slop": 0,
                    "prefix_length": 0,
                    "max_expansions": 50,
                    "zero_terms_query": "NONE",
                    "auto_generate_synonyms_phrase_query": true,
                    "fuzzy_transpositions": true,
                    "boost": 1.0
                }
            }
            """
    }

}