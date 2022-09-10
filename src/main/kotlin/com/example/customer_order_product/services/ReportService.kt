package com.example.customer_order_product.services

import com.example.customer_order_product.models.Staff
import com.example.customer_order_product.repo.CustomerRepo
import com.example.customer_order_product.repo.OrderRepo
import com.example.customer_order_product.repo.StaffRepo
import com.example.customer_order_product.requestClass.KeyOperationQueryRequest
import com.example.customer_order_product.specifition.SearchCriteria
import com.example.customer_order_product.specifition.SpecificationBuilder
import com.example.customer_order_product.specifition.StaffSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service


@Service
class ReportService {
    @Autowired
    lateinit var staffRepo:StaffRepo

    @Autowired
    lateinit var customerRepo: CustomerRepo

    @Autowired
    lateinit var orderRepo: OrderRepo

    fun getStaffByKeyOperationValue(staffQuery: KeyOperationQueryRequest):Any?{

        if(staffQuery.key?.size != staffQuery.value?.size && staffQuery.key?.size != staffQuery.operation?.size){
            return null
        }
        return staffRepo.findAll(SpecificationBuilder<Staff>().build(staffQuery))
    }
}