package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.*
import org.elasticsearch.indices.*
import org.junit.*

class TermsLookupTest {

    @Test
    fun `test terms lookup`() {
        val query = termsLookup {
            "user" to TermsLookup("users", "user", "2", "followers")
        }

        query should_render_as """
            {
                "terms" : {
                    "user" : {
                        "index" : "users",
                        "type" : "user",
                        "id" : "2",
                        "path" : "followers"
                    },
                    "boost": 1.0
                }
            }
            """
    }

}