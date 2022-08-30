package com.example.customer_order_product.controller

import com.example.customer_order_product.DAO
import com.example.customer_order_product.requestClass.OrderRequest
import com.example.customer_order_product.services.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/customer/")
class CustomerControlller(private val customerService: CustomerService, private val dao:DAO) {
    @GetMapping("{id}")
    fun getCustomerById(@PathVariable id:Long): ResponseEntity<Any>? {
        val customer = customerService.getById(id)
        customer?:return ResponseEntity.notFound().build()
        return ResponseEntity.ok(customer)
    }

    @PostMapping("/order")
    fun orderProduct(@RequestBody orderRequest:OrderRequest):ResponseEntity<Any>{
        if(customerService.createOrder(dao.getCustomer(orderRequest.cus_id),
                dao.getAllProductQuantity(orderRequest.pro_ids_quantities),
                dao.getStaff(orderRequest.staff_id)))
            return ResponseEntity.accepted().build()
        return ResponseEntity.badRequest().build()
    }
}