/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_render_as
import org.elasticsearch.common.unit.Fuzziness
import org.junit.Test

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
                    "value" : "ki"
                }
            }
        }
        """
    }

    @Test
    fun `test advanced fuzzy`() {
        val query = fuzzy {
            "user" to {
                value = "ki"
                boost = 1.0f
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
                    "boost" :         1.0,
                    "fuzziness" :     "2",
                    "prefix_length" : 0,
                    "max_expansions": 100
                }
            }
        }
        """
    }
}