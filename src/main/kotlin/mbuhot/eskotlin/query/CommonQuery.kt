package mbuhot.eskotlin.query

import org.elasticsearch.index.query.AbstractQueryBuilder

/**
 * Created on 8/11/16.
 * @author Ryan Murfitt (rmurfitt@gmail.com)
 */

open class QueryData {
    var boost: Float? = null
}

fun AbstractQueryBuilder<*>.initQuery(data: QueryData) {
   data.boost?.let { this.boost(it) }
}
