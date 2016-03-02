/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.term
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class BoostingTest {

    @Test
    fun `test boosting`() {
        val query = boosting {
            positive {
                term {
                    "field1" to "value1"
                }
            }
            negative {
                term {
                    "field2" to "value2"
                }
            }
            negative_boost = 0.2f
            boost = 0.1f
        }

        query should_render_as """
            {
                "boosting" : {
                    "positive" : {
                        "term" : {
                            "field1" : "value1"
                        }
                    },
                    "negative" : {
                        "term" : {
                            "field2" : "value2"
                        }
                    },
                    "negative_boost" : 0.2,
                    "boost" : 0.1
                }
            }
            """
    }
}