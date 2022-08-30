package com.example.customer_order_product.requestClass

data class PaymentRequest (
    val order_id:Long,
    val customer_id:Long,
    val staff_id:Long,
    val pay_amount:Double
        )