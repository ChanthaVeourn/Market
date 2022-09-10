package com.example.customer_order_product.requestClass

data class KeyOperationQueryRequest (
    val key:List<String>?=null,
    val operation:List<String>?=null,
    val value:List<Any>?=null
)