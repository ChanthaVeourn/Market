package com.example.customer_order_product.services

import com.example.customer_order_product.dto.IStaffReportDto
import com.example.customer_order_product.models.Staff
import com.example.customer_order_product.repo.CustomerRepo
import com.example.customer_order_product.repo.OrderRepo
import com.example.customer_order_product.repo.StaffRepo
import com.example.customer_order_product.utils.SearchCriteria
import com.example.customer_order_product.utils.StaffSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReportService {
    @Autowired
    lateinit var staffRepo:StaffRepo

    @Autowired
    lateinit var customerRepo: CustomerRepo

    @Autowired
    lateinit var orderRepo: OrderRepo

    fun getStaffByKeyOperationValue(key:String,operation:String, value:Any):Any{
        val spec = StaffSpecification(SearchCriteria(key, operation, value))
        return staffRepo.findAll(spec)
    }

}