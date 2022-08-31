package com.example.customer_order_product.requestClass

import java.math.BigDecimal

data class PaymentRequest (
    val order_id:Long,
    val customer_id:Long,
    val staff_id:Long,
    val pay_amount:Double
        )