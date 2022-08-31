package com.example.customer_order_product.services

import com.example.customer_order_product.dto.CustomerDto
import com.example.customer_order_product.models.*
import com.example.customer_order_product.repo.CustomerRepo
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CustomerService(private val customerRepo: CustomerRepo) {

    fun getById(id:Long): Customer?{
        return customerRepo.findById(id).orElse(null)
    }

    fun getByPhone(phone:String):CustomerDto?{
        return customerRepo.findByPhone(phone)
    }

    fun createOrder(customer:Customer?, products_quantities:Map<Product?, Int>?, staff: Staff?):Boolean{
        customer?: return false
        staff?: return false
        products_quantities?: return false
        val newOrder = Order(customer,staff)
        val orderDetailsList = mutableListOf<OrderDetail>()
        var total = BigDecimal.valueOf(0.0)
        products_quantities.forEach{(product, quantity) ->
            val price = product?.unitPrice
            val totalPrice = price!!*BigDecimal.valueOf(quantity.toLong())
            total+=totalPrice
            orderDetailsList.add(OrderDetail(newOrder, product, price, quantity, totalPrice))
        }
        newOrder.also {
            it.totalAmount = total
            it.orderDetails?.addAll(orderDetailsList)
        }
        val invoiceDetail = InvoiceDetail(staff = null,
            customer = customer,
            order = newOrder,
            amount_to_pay = total)

        customer.orders?.add(newOrder)
        customer.invoiceDetails?.add(invoiceDetail)
        customerRepo.save(customer)
        return true
    }



}