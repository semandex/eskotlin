/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */
package mbuhot.eskotlin.query

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat


val jsonMapper = ObjectMapper()
fun json_normalize(str: String) : String = jsonMapper.readTree(str).let { jsonMapper.writeValueAsString(it) }

infix fun <T> T.should_render_as(jsonStr: String) {
    assertThat(
        json_normalize(this.toString()),
        equalTo(json_normalize(jsonStr)))
}

