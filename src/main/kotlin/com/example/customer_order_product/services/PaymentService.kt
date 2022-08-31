package com.example.customer_order_product.services

import com.example.customer_order_product.models.*
import com.example.customer_order_product.repo.PaymentRepo
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentService(private val paymentRepo: PaymentRepo) {
    fun createPayment(order: Order?, customer: Customer?, staff: Staff?, pay_amount:Double ):Boolean{
        order?: return false
        customer?: return false
        staff?: return false
        if(pay_amount <= 0.0) return false
        if (order.paidStatus) return false
        if(!order.payingStatus) order.payingStatus = true
        if(!order.invoiceDetails.isNullOrEmpty()) {

            val lastInvoice = order.invoiceDetails!![order.invoiceDetails!!.lastIndex]
            lastInvoice.also {
                it.staff = staff
                it.paid = BigDecimal.valueOf(pay_amount)
            }

            val payment =  Payment(customer, order, staff, lastInvoice, BigDecimal.valueOf(pay_amount))

            if(BigDecimal.valueOf(pay_amount).compareTo(lastInvoice.amount_to_pay) == 0){
                order.paidStatus = true
                paymentRepo.save(payment)
                return true
            }

            val toPay = lastInvoice.amount_to_pay - BigDecimal.valueOf(pay_amount)
            val newInvoiceDetail = InvoiceDetail(staff = null,
                customer = customer,
                order = order,
                amount_to_pay = toPay)
            customer.invoiceDetails?.add(newInvoiceDetail)
            paymentRepo.save(payment)
        }
        return true
    }
}
