/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.junit.jupiter.api.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class ConstantScoreTest {
    @Test
    fun `test constant_score`() {
        val query = constant_score {
            filter {
                term {
                    "user" to "kimchy"
                }
            }
            boost = 1.2f
        }

        query should_render_as """
        {
            "constant_score": {
                "filter": {
                    "term": {
                        "user": {
                            "value": "kimchy",
                            "boost": 1.0
                        }
                    }
                },
                "boost": 1.2
            }
        }
        """
    }
}