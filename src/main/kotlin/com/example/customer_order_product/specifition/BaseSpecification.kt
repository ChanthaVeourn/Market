package com.example.customer_order_product.specifition

import com.sun.xml.bind.v2.util.TypeCast
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

  data class SearchCriteria(
     val key:String?=null,
     val operation:String?=null,
     val value:Any? = null
)

open class BaseSpecification<T>(private val searchCriteria: SearchCriteria):Specification<T> {

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? {
        if(searchCriteria.key != null && searchCriteria.operation != null && searchCriteria.value != null) {
            if (searchCriteria.operation == "=")
                return cb.equal(root.get<Any>(searchCriteria.key), searchCriteria.value)
            if (searchCriteria.operation == "like")
                return cb.like(cb.lower(root.get(searchCriteria.key)), "%${searchCriteria.value.toString()
                    .lowercase(Locale.getDefault())}%")
            if(searchCriteria.operation == "in"){
                val setOfValue:List<*> = searchCriteria.value.let{it as List<*>}
                return cb.and(root.get<Any>(searchCriteria.key).`in`(setOfValue))
            }
            if(searchCriteria.operation == "[a]"){
                val startEnd = searchCriteria.value as List<*>
                val start = startEnd[0]!! as String
                val end = startEnd[1]!! as String
                return cb.between(root.get(searchCriteria.key), start, end)
            }
            if(searchCriteria.operation == "[1]"){
                val startEnd = searchCriteria.value as List<*>
                val start = startEnd[0]!! as Int
                val end = startEnd[1]!! as Int
                return cb.between(root.get(searchCriteria.key), start, end)
            }
        }
        return null
    }
}