package com.example.customer_order_product.controller

import com.example.customer_order_product.services.ReportService
import com.example.customer_order_product.services.StaffService
import com.example.customer_order_product.utils.StaffKeyOperationQuery
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/report/")
class ReportController(private val reportService: ReportService,
                       private val staffService: StaffService) {

    @GetMapping("staff-key-operation")
    fun getById(@RequestBody queryOption:StaffKeyOperationQuery?):ResponseEntity<Any>{
        if(queryOption?.key == null && queryOption?.operation == null && queryOption?.value == null){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity.ok(reportService.getStaffByKeyOperationValue(queryOption.key!!, queryOption.operation!!, queryOption.value!!))
    }

    @GetMapping("top-seller")
    fun getTopSellerOf(@RequestParam start:String, end:String): ResponseEntity<List<Any>> {
        staffService.getTopSellerPageableOf(start, end)?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(staffService.getTopSellerPageableOf(start, end))
    }

    @GetMapping("cashier-income-of-year")
    fun getTopCashierOfYear(@RequestParam year:String):ResponseEntity<List<Any>>{
        staffService.getAllCashierOfYearOrderIncome(year)?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(staffService.getAllCashierOfYearOrderIncome(year))
    }

    @GetMapping("top-seller-row")
    fun getTopStaffLimitRow(@RequestParam row: Int, @RequestParam year: Int):ResponseEntity<Any>{
        staffService.getTopStaffOfYearLimitRow(year, row)?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(staffService.getTopStaffOfYearLimitRow(year, row))
    }
}