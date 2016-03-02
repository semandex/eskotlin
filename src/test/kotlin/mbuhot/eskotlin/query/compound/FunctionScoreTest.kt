/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.match_all
import mbuhot.eskotlin.query.term.term
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.*
import org.junit.Test

/**
 * Created on 3/03/2016

 * @author Michael Buhot (m.buhot@gmail.com)
 */
class FunctionScoreTest {

    @Test
    fun `test function_score`() {
        val query = function_score {
            query = match_all { }
            functions = listOf(
                term { "foo" to "bar" } to gaussDecayFunction("baz", 1.0),
                match_all { } to randomFunction(234L),
                null to exponentialDecayFunction("qux", 2.3))

            boost = 1.2f
            boost_mode = "multiply"
            score_mode = "max"
            max_boost = 5.0f
            min_score = 0.001f
        }

        query should_render_as """{
            "function_score": {
                "query": {
                    "match_all": {}
                },
                "functions": [
                    {
                        "filter": {
                            "term": {
                                "foo": "bar"
                            }
                        },
                        "gauss": {
                            "baz": {
                                "scale": 1.0
                            }
                        }
                    },
                    {
                        "filter": {
                            "match_all": {}
                        },
                        "random_score": {
                            "seed": 234
                        }
                    },
                    {
                        "exp": {
                            "qux": {
                                "scale": 2.3
                            }
                        }
                    }
                ],
                "score_mode": "max",
                "boost_mode": "multiply",
                "max_boost": 5.0,
                "boost": 1.2,
                "min_score": 0.001
            }
        }
        """
    }
}
