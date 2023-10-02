/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_render_as
import org.opensearch.index.query.RegexpFlag.*
import org.junit.jupiter.api.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class RegexpTest {

    @Test
    fun `test regexp`() {
        val query = regexp {
            "name.first" to "s.*y"
        }

        query should_render_as """
        {
            "regexp" : {
                "name.first": {
                    "value": "s.*y",
                    "flags_value": ${ALL.value()},
                    "max_determinized_states" : 10000,
                    "boost": 1.0
                }
            }
        }
        """
    }

    @Test
    fun `test regexp with boost and flags`() {
        val query = regexp {
            "name.first" {
                value = "s.*y"
                boost = 1.2f
                flags = listOf(INTERSECTION, COMPLEMENT, EMPTY)
            }
        }
        query should_render_as """
        {
            "regexp" : {
                "name.first" : {
                    "value" : "s.*y",
                    "flags_value" : ${INTERSECTION.value() or COMPLEMENT.value() or EMPTY.value()},
                    "max_determinized_states" : 10000,
                    "boost" : 1.2
                }
            }
        }
        """
    }
}