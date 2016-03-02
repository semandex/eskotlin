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
class TypeTest {
    @Test
    fun `test type`() {
        val query = type {
            value = "my_type"
        }
        query should_render_as """
        {
            "type": {
                "value" : "my_type"
            }
        }
        """
    }
}