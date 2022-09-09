package com.example.customer_order_product.repo

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import kotlin.reflect.KProperty1

interface BaseRepo<T>:JpaSpecificationExecutor<T> {

}