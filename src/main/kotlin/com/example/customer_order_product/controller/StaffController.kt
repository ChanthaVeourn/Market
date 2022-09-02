package com.example.customer_order_product.controller

import com.example.customer_order_product.services.StaffService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.persistence.TemporalType

@RestController
@RequestMapping("/api/staff/")
class StaffController(private val staffService: StaffService) {

    @GetMapping("top-seller")
    fun getTopSellerOf(@RequestParam start:String, end:String):ResponseEntity<List<Any>>{
        staffService.getTop5SellerOf(start, end)?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(staffService.getTop5SellerOf(start, end))
    }

    @GetMapping("top-cashier")
    fun getTopCashierOfYear(@RequestParam year:String):ResponseEntity<List<Any>>{
        staffService.getTopCashier(year)?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(staffService.getTopCashier(year))
    }
}