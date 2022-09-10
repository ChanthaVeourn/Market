package com.example.customer_order_product.specifition

import com.example.customer_order_product.requestClass.KeyOperationQueryRequest
import org.springframework.data.jpa.domain.Specification

class SpecificationBuilder<T> {

    fun build(keyOperation: KeyOperationQueryRequest):Specification<T>{
        val specs: MutableList<BaseSpecification<T>> = mutableListOf()
        for(index in 0 until keyOperation.key!!.size) {
            val spec = BaseSpecification<T>(SearchCriteria(keyOperation.key[index],
                keyOperation.operation!![index], keyOperation.value!![index]
            ))
            specs.add(spec)
        }

        var toQuery:Specification<T> = specs[0]
        for(i in 1 until specs.size)
            toQuery = Specification.where(toQuery).and(specs[i])

        return toQuery
    }
}