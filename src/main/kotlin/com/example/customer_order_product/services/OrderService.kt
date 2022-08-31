package com.example.customer_order_product.services

import com.example.customer_order_product.dto.OrderDetailDto
import com.example.customer_order_product.dto.OrderDto
import com.example.customer_order_product.models.Order
import com.example.customer_order_product.models.OrderDetail
import com.example.customer_order_product.models.Product
import com.example.customer_order_product.repo.OrderRepo
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService(private val orderRepo: OrderRepo) {

    fun getAllCusOrder(cus_id:Long):List<OrderDto>?{
       return orderRepo.findByCustomerId(cus_id)
    }

    fun getAllOrders():List<Order>?{
        return orderRepo.findAll()
    }

    fun getAllItems(order:Order?):List<OrderDetailDto>?{
        val orderItemsList = mutableListOf<OrderDetailDto>()
        order?: return null
        order.orderDetails?.forEach{
            orderItemsList
                .add(
                    OrderDetailDto(
                        it.id!!,
                        it.product.id!!,
                        it.order.id!!,
                        it.unitPrice,
                        it.quantity,
                        it.total)) }
        return orderItemsList
    }

    fun removeOrder( order:Order?):Boolean{
        order?: return false
        if(!order.payingStatus && !order.paidStatus || order.payingStatus && order.paidStatus){
            orderRepo.delete(order)
            return true
        }
        return false
    }

    fun removeOrderItem( order: Order?, orderDetailId:Long):Boolean{

        order?: return false

        if(!order.payingStatus && !order.paidStatus || order.payingStatus && order.paidStatus){

        val itemToRemove = order.orderDetails!!.first { it.id == orderDetailId }
        order.also {
            it.orderDetails!!.remove(itemToRemove)
            it.totalAmount = order.totalAmount?.minus(itemToRemove.total)
        }
        if(order.orderDetails?.isEmpty() == true) {
            orderRepo.delete(order)
            return true
        }
        orderRepo.save(order)
        return true
        }
        return false
    }

    fun addMoreItems(order:Order?, products_quantities:Map<Product?, Int>?):Boolean{

        order?: return false
        if(order.payingStatus) return false
        products_quantities?: return false

        val orderDetailsList = mutableListOf<OrderDetail>()
        var total = BigDecimal.valueOf(0.0)
        val existProduct = order.orderDetails?.map { it.product }

        products_quantities.forEach{(product, quantity) ->
            if(quantity > 0)
            if(product != null){
                if(!existProduct.isNullOrEmpty() && product in existProduct){
                    order.orderDetails?.first { it.product == product }?.also {
                            total -= it.total
                            it.quantity += quantity
                            it.total = BigDecimal.valueOf(it.quantity.toLong()) * product.unitPrice
                            total += it.total
                        }
                }
                else{
                    val price = product.unitPrice
                    val totalPrice = price*BigDecimal.valueOf(quantity.toLong())
                    total+=totalPrice
                    orderDetailsList.add(OrderDetail(order, product, price, quantity, totalPrice))
                }
            }
        }

        order.totalAmount = order.totalAmount?.plus(total)
        order.orderDetails?.addAll(orderDetailsList)
        orderRepo.save(order)
        return true
    }

    fun updateOrderItems(order: Order?, products_quantities:Map<Product?, Int>?):Boolean{

        order?: return false
        if(!order.payingStatus) return false
        products_quantities?: return false
        order.orderDetails?: return false

        var preTotal = BigDecimal.valueOf(0.0)
        var newTotal = BigDecimal.valueOf(0.0)

        val productToRemove = mutableSetOf<Product>()

        order.orderDetails?.map{ orderDetail ->
            if(orderDetail.product in products_quantities.keys){
                preTotal+=orderDetail.total
                if(products_quantities[orderDetail.product]!! <= 0){
                    productToRemove.add(orderDetail.product)
                }
                else {
                    orderDetail.quantity = products_quantities[orderDetail.product]!!
                    orderDetail.total = BigDecimal.valueOf(orderDetail.quantity.toLong()) * orderDetail.product.unitPrice
                    newTotal += orderDetail.total
                }
            }
        }

        if(productToRemove.isNotEmpty()){
            order.also { o -> o.orderDetails?.removeAll { it.product in productToRemove }}
        }
        order.totalAmount = order.totalAmount?.minus(preTotal)?.plus(newTotal)
        orderRepo.save(order)
        return true
    }
}







