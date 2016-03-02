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
            values = listOf("1", "4", "100")
        }

        query should_render_as """
        {
            "ids" : {
                "types" : [],
                "values" : ["1", "4", "100"]
            }
        }
        """
    }

    @Test
    fun `test ids with type`() {
        val query = ids {
            type = "my_type"
            values = listOf("1", "4", "100")
        }

        query should_render_as """
        {
            "ids" : {
                "type" : "my_type",
                "values" : ["1", "4", "100"]
            }
        }
        """
    }

    @Test
    fun `test ids with multiple types`() {
        val query = ids {
            types = listOf("my_type", "other_type")
            values = listOf("1", "4", "100")
        }

        query should_render_as """
        {
            "ids" : {
                "types" : ["my_type", "other_type"],
                "values" : ["1", "4", "100"]
            }
        }
        """
    }
}