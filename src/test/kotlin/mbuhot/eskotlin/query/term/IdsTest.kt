/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.should_render_as
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class IdsTest {
    @Test
    fun `test ids`() {
        val query = ids {
            values = listOf("1", "100", "4")
        }

        query should_render_as """
        {
            "ids" : {
                "values" : ["1", "100", "4"],
                "boost": 1.0
            }
        }
        """
    }
}