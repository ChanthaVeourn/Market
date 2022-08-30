package com.example.customer_order_product.repo

import com.example.customer_order_product.models.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepo:JpaRepository<Category, Long> {
}