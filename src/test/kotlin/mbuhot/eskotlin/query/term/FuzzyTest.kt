/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_render_as
import org.opensearch.common.unit.Fuzziness
import org.junit.jupiter.api.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class FuzzyTest {
    @Test
    fun `test fuzzy`() {
        val query = fuzzy {
            "user" to "ki"
        }
        query should_render_as """
        {
            "fuzzy" : {
                "user" : {
                    "value" : "ki",
                    "fuzziness" : "AUTO",
                    "prefix_length" : 0,
                    "max_expansions" : 50,
                    "transpositions" : false,
                    "boost" : 1.0
                }
            }
        }
        """
    }

    @Test
    fun `test advanced fuzzy`() {
        val query = fuzzy {
            "user" {
                value = "ki"
                boost = 2.0f
                fuzziness = Fuzziness.TWO
                prefix_length = 0
                max_expansions = 100
            }
        }
        query should_render_as """
        {
            "fuzzy" : {
                "user" : {
                    "value" :         "ki",
                    "fuzziness" :     "2",
                    "prefix_length" : 0,
                    "max_expansions": 100,
                    "transpositions" : false,
                    "boost" :         2.0
                }
            }
        }
        """
    }
}