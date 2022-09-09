package com.example.customer_order_product.utils

import com.example.customer_order_product.models.Staff
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

class StaffSpecification(private val searchCriteria: SearchCriteria):Specification<Staff> {

    override fun toPredicate(root: Root<Staff>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? {
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
        }
        return null
    }
}




