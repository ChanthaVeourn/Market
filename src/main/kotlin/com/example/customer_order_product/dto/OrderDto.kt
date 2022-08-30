package com.example.customer_order_product.dto

import java.time.LocalDate

class OrderDto (
    val id:Long,
    val customerId:Long,
    val createdDate:LocalDate,
    val totalAmount: Double,
    val paidStatus:Boolean
)