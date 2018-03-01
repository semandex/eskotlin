package mbuhot.eskotlin.query.term

import mbuhot.eskotlin.query.*
import org.elasticsearch.index.query.*
import org.elasticsearch.indices.*

class TermsLookupBlock {
    class TermsData(
            var name: String,
            var lookup: TermsLookup) : QueryData()

    infix fun String.to(lookup: TermsLookup): TermsData {
        return TermsData(name = this, lookup = lookup)
    }
}

fun termsLookup(init: TermsLookupBlock.() -> TermsLookupBlock.TermsData): TermsQueryBuilder {
    val params = TermsLookupBlock().init()
    return TermsQueryBuilder(params.name, params.lookup).apply {
        initQuery(params)
    }
}