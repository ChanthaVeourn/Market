package com.example.customer_order_product.services

import com.example.customer_order_product.dto.OrderDto
import com.example.customer_order_product.models.Order
import com.example.customer_order_product.models.OrderDetail
import com.example.customer_order_product.models.Product
import com.example.customer_order_product.repo.OrderRepo
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepo: OrderRepo) {

    fun getAllCusOrder(cus_id:Long):List<OrderDto>?{
       return orderRepo.findByCustomerId(cus_id)
    }

    fun removeOrder( order:Order?):Boolean{
        order?: return false
        orderRepo.delete(order)
        return true
    }

    fun removeOrderItem( order: Order?, orderDetailId:Long):Boolean{

        order?: return false
        val itemToRemove = order.orderDetails!!.first { it.id == orderDetailId }
        order.orderDetails!!.remove(itemToRemove)
        order.totalAmount = order.totalAmount?.minus(itemToRemove.total)
        if(order.orderDetails?.isEmpty()!!) {
            orderRepo.delete(order)
            return true
        }
        orderRepo.save(order)
        return true
    }

    fun addMoreItems(order:Order?, products_quantities:Map<Product?, Int>?):Boolean{
        order?: return false
        if(order.payingStatus) return false
        products_quantities?: return false
        val orderDetailsList = mutableListOf<OrderDetail>()
        var total = 0.0
        val existProduct = order.orderDetails?.map { it.product }

        products_quantities.forEach{(product, quantity) ->
            if(quantity > 0)
            if(product != null){
                if(!existProduct.isNullOrEmpty() && product in existProduct){
                    order.orderDetails?.first { it.product == product }?.also {
                            total -= it.total
                            it.quantity += quantity
                            it.total = it.quantity * product.unitPrice
                            total+=it.total
                        }
                }
                else{
                    val price = product.unitPrice
                    val totalPrice = price*quantity
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
        var preTotal = 0.0
        var newTotal = 0.0

        val productToRemove = mutableSetOf<Product>()
        order.orderDetails?.map{ orderDetail ->
            if(orderDetail.product in products_quantities.keys){
                preTotal+=orderDetail.total
                if(products_quantities[orderDetail.product]!! <= 0){
                    productToRemove.add(orderDetail.product)
                }
                else {
                    orderDetail.quantity = products_quantities[orderDetail.product]!!
                    orderDetail.total = orderDetail.quantity * orderDetail.product.unitPrice
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







