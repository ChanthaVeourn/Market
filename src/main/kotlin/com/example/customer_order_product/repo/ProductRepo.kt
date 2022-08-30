package com.example.customer_order_product.repo

import com.example.customer_order_product.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo:JpaRepository<Product, Long> {
}