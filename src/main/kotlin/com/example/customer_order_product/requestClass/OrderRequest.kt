package com.example.customer_order_product.requestClass

import com.example.customer_order_product.models.Product


data class OrderRequest (
    val cus_id:Long? = null,
    val staff_id:Long? = null,
    val order_id:Long? = null,
    val pro_ids_quantities:Map<String, Int>
    )