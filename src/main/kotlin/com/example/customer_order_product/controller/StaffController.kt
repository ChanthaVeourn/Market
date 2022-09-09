package com.example.customer_order_product.controller

import com.example.customer_order_product.dto.IStaffReportDto
import com.example.customer_order_product.repo.StaffRepo
import com.example.customer_order_product.services.StaffService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.TemporalType

@RestController
@RequestMapping("/api/staff/")
class StaffController(private val staffRepo: StaffRepo) {

    @GetMapping
    fun getAllStaff():ResponseEntity<Any>{
        staffRepo.getAll()?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(staffRepo.getAll())
    }
}

