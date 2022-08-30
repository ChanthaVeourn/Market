package com.example.customer_order_product.controller

import com.example.customer_order_product.DAO
import com.example.customer_order_product.requestClass.PaymentRequest
import com.example.customer_order_product.services.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/payment/")
class PaymentController(private val paymentService: PaymentService,private val  dao: DAO) {

    @PostMapping
    fun createPayment(@RequestBody paymentRequest: PaymentRequest):ResponseEntity<Any>{
        if(paymentService.createPayment(dao.getOrder(paymentRequest.order_id),
            dao.getCustomer(paymentRequest.customer_id),
            dao.getStaff(paymentRequest.staff_id),
            paymentRequest.pay_amount)) return ResponseEntity.accepted().build()
        return ResponseEntity.badRequest().build()
    }
}