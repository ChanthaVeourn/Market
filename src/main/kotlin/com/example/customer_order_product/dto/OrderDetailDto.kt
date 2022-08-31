package com.example.customer_order_product.dto

import java.math.BigDecimal

data class OrderDetailDto (
    val id:Long,
    val productId:Long,
    val orderId:Long,
    val unitPrice:BigDecimal,
    val quantity:Int,
    val total:BigDecimal
        )