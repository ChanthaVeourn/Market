package com.example.customer_order_product.services

import com.example.customer_order_product.models.*
import com.example.customer_order_product.repo.PaymentRepo
import org.springframework.stereotype.Service

@Service
class PaymentService(private val paymentRepo: PaymentRepo) {
    fun createPayment(order: Order?, customer: Customer?, staff: Staff?, pay_amount:Double ):Boolean{
        order?: return false
        customer?: return false
        staff?: return false
        if(pay_amount <= 0.0) return false
        if (!order.payingStatus) order.payingStatus = true
        val lastInvoice:InvoiceDetail
        if(!order.invoiceDetails.isNullOrEmpty()) {
            lastInvoice = order.invoiceDetails!![order.invoiceDetails!!.lastIndex]
            lastInvoice.staff = staff
            val payment =  Payment(customer, order, staff, lastInvoice, pay_amount)
            if(pay_amount == lastInvoice.amount_to_pay){
                order.paidStatus = true
                paymentRepo.save(payment)
                return true
            }
            val newInvoiceDetail = InvoiceDetail(staff = null,
                customer = customer,
                order = order,
                amount_to_pay = lastInvoice.amount_to_pay - pay_amount)
            customer.invoiceDetails?.add(newInvoiceDetail)
            paymentRepo.save(payment)
        }
        return true
    }
}