/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_render_as
import org.elasticsearch.indices.TermsLookup
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class TermsTest {

    @Test
    fun `test terms`() {
        val query = terms {
            "user" to listOf("kimchy", "elasticsearch")
        }

        query should_render_as """
            {
                "terms" : {
                    "user" : ["kimchy", "elasticsearch"],
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test terms lookup`() {
        val query = terms {
            "user" to TermsLookup("users", "user", "2", "followers")
        }

        query should_render_as """
            {
                "terms" : {
                    "user" : {
                        "index" : "users",
                        "type" : "user",
                        "id" : "2",
                        "path" : "followers"
                    },
                    "boost": 1.0
                }
            }
            """
    }

    @Test
    fun `test terms lookup dsl`() {
        val query = terms {
            "user" {
                index = "users"
                type = "user"
                id = "2"
                path = "followers"
            }
        }

        query should_render_as """
            {
                "terms" : {
                    "user" : {
                        "index" : "users",
                        "type" : "user",
                        "id" : "2",
                        "path" : "followers"
                    },
                    "boost": 1.0
                }
            }
            """
    }
}