/*
 * Copyright (c) 2021. Ehsan Souri ehsansouri23@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

class StringQueryTest {

    @Test
    fun `test string contains query`() {
        val query = string {
            "field" contains "f"
        }

        query should_render_as """
        {
            "query_string":{
                  "query":"*f*",
                  "fields":[
                     "field^1.0"
                  ],
                  "type":"best_fields",
                  "default_operator":"and",
                  "max_determinized_states":10000,
                  "enable_position_increments":true,
                  "fuzziness":"AUTO",
                  "fuzzy_prefix_length":0,
                  "fuzzy_max_expansions":50,
                  "phrase_slop":0,
                  "analyze_wildcard":true,
                  "escape":false,
                  "auto_generate_synonyms_phrase_query":true,
                  "fuzzy_transpositions":true,
                  "boost":1.0
            }
    }   
        """.trimIndent()
    }

    @Test
    fun `test string starts with query`() {
        val query = string {
            "field" startsWith "f"
        }

        query should_render_as """
        {
            "query_string":{
                  "query":"f*",
                  "fields":[
                     "field^1.0"
                  ],
                  "type":"best_fields",
                  "default_operator":"and",
                  "max_determinized_states":10000,
                  "enable_position_increments":true,
                  "fuzziness":"AUTO",
                  "fuzzy_prefix_length":0,
                  "fuzzy_max_expansions":50,
                  "phrase_slop":0,
                  "analyze_wildcard":true,
                  "escape":false,
                  "auto_generate_synonyms_phrase_query":true,
                  "fuzzy_transpositions":true,
                  "boost":1.0
            }
    }   
        """.trimIndent()
    }

    @Test
    fun `test string ends with query`() {
        val query = string {
            "field" endsWith "f"
        }

        query should_render_as """
        {
            "query_string":{
                  "query":"*f",
                  "fields":[
                     "field^1.0"
                  ],
                  "type":"best_fields",
                  "default_operator":"and",
                  "max_determinized_states":10000,
                  "enable_position_increments":true,
                  "fuzziness":"AUTO",
                  "fuzzy_prefix_length":0,
                  "fuzzy_max_expansions":50,
                  "phrase_slop":0,
                  "analyze_wildcard":true,
                  "escape":false,
                  "auto_generate_synonyms_phrase_query":true,
                  "fuzzy_transpositions":true,
                  "boost":1.0
            }
    }   
        """.trimIndent()
    }

}