package com.example.customer_order_product.dto

import java.math.BigDecimal

interface IStaffReportDto {
    fun getId():Long? = null
    fun getName():String? = null
    fun getSalary():BigDecimal? = null
    fun getTotal():BigDecimal? = null
}