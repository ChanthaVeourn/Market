package com.example.customer_order_product.services

import com.example.customer_order_product.repo.StaffRepo
import org.springframework.stereotype.Service

@Service
class StaffService(private val staffRepo: StaffRepo) {
    fun getTop5SellerOf(start:String, end:String):List<Any>?{
        return staffRepo.getTopSellerOf(start, end)
    }
    fun getTopCashier(year:String):List<Any>?{
        return staffRepo.getTopCashier(year)
    }
}