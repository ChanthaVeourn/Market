package com.example.customer_order_product.repo

import com.example.customer_order_product.dto.OrderDto
import com.example.customer_order_product.models.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepo:JpaRepository<Order, Long> {
    fun findByOrderDetailsId(oderDetailId:Long):Order?
    fun findByCustomerId(cus_id:Long):List<OrderDto>?
}