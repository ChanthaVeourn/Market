package com.example.customer_order_product.services

import com.example.customer_order_product.dto.IStaffReportDto
import com.example.customer_order_product.repo.StaffRepo
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class StaffService(private val staffRepo: StaffRepo) {

    fun getTopSellerPageableOf(start:String, end:String): List<IStaffReportDto>? {
        return staffRepo.getTopSellerPageableOfDuration(start, end, PageRequest.of(2, 5))?.content
    }

    fun getAllCashierOfYearOrderIncome(year:String):List<Any>?{
        return staffRepo.getAllCashierOfYearOrderIncome(year)
    }

    fun getTopStaffOfYearLimitRow(year:Int, topNumber:Int):List<IStaffReportDto>?{
        return staffRepo.getTopSellerLimitRowOfYear(year, topNumber)
    }
}