package com.example.customer_order_product.dto

import java.math.BigDecimal
import java.time.LocalDate

class OrderDto (
    val id:Long,
    val customerId:Long,
    val createdDate:LocalDate,
    val totalAmount: BigDecimal,
    val paidStatus:Boolean
)