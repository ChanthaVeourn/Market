package com.example.customer_order_product.repo

import com.example.customer_order_product.dto.CustomerDto
import com.example.customer_order_product.models.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepo:JpaRepository<Customer, Long> {

    fun findByNameStartingWith(st:String): List<CustomerDto>?
    fun findByName(name:String):List<CustomerDto>?
    fun findByPhone(phone:String):CustomerDto?

}