/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

package mbuhot.eskotlin.query.compound

import mbuhot.eskotlin.query.should_render_as
import mbuhot.eskotlin.query.term.match_all
import mbuhot.eskotlin.query.term.term
import org.opensearch.index.query.functionscore.ScoreFunctionBuilders.*
import org.junit.jupiter.api.Test

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
                term { "foo" to "bar" } to gaussDecayFunction("baz", 1.0, "1d"),
                match_all { } to randomFunction().seed(234L),
                null to exponentialDecayFunction("qux", 2.3, "10km"))

            boost = 1.2f
            boost_mode = "multiply"
            score_mode = "max"
            max_boost = 5.0f
            min_score = 0.001f
        }

        query should_render_as """{
            "function_score": {
                "query": {
                    "match_all": {
                        "boost": 1.0
                    }
                },
                "functions": [{
                    "filter": {
                        "term": {
                            "foo": {
                                "value": "bar",
                                "boost": 1.0
                            }
                        }
                    },
                    "gauss": {
                        "baz": {
                            "origin": 1.0,
                            "scale": "1d",
                            "decay": 0.5
                        },
                        "multi_value_mode": "MIN"
                    }
                }, {
                    "filter": {
                        "match_all": {
                            "boost": 1.0
                        }
                    },
                    "random_score": {
                        "seed": 234
                    }
                }, {
                    "filter": {
                        "match_all": {
                            "boost": 1.0
                        }
                    },
                    "exp": {
                        "qux": {
                            "origin": 2.3,
                            "scale": "10km",
                            "decay": 0.5
                        },
                        "multi_value_mode": "MIN"
                    }
                }],
                "score_mode": "max",
                "boost_mode": "multiply",
                "max_boost": 5.0,
                "min_score": 0.001,
                "boost": 1.2
            }
        }
        """
    }

    @Test
    fun `test field_value_factor`() {
        val query = function_score {
            query = match_all { }
            field_value_factor = fieldValueFactorFunction("someField")
            boost = 1.2f
            boost_mode = "multiply"
            score_mode = "max"
            max_boost = 5.0f
            min_score = 0.001f
        }
        
        query should_render_as """{
              "function_score" : {
                "query" : {
                  "match_all" : {
                    "boost" : 1.0
                  }
                },
                "functions" : [
                  {
                    "filter" : {
                      "match_all" : {
                        "boost" : 1.0
                      }
                    },
                    "field_value_factor" : {
                      "field" : "someField",
                      "factor" : 1.0,
                      "modifier" : "none"
                    }
                  }
                ],
                "score_mode" : "max",
                "boost_mode" : "multiply",
                "max_boost" : 5.0,
                "min_score" : 0.001,
                "boost" : 1.2
              }
            }
        """
    }
}
