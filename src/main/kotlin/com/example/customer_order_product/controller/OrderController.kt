package com.example.customer_order_product.controller

import com.example.customer_order_product.DAO
import com.example.customer_order_product.requestClass.OrderRequest
import com.example.customer_order_product.services.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/order/")
class OrderController(private val orderService: OrderService, private val dao: DAO) {

    @GetMapping
    fun getAllOrders():ResponseEntity<Any>{
        orderService.getAllOrders()?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(orderService.getAllOrders())
    }

    @GetMapping("customer")
    fun allCustomerOrder(@RequestParam cus_id:Long):ResponseEntity<Any>{
        orderService.getAllCusOrder(cus_id)?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(orderService.getAllCusOrder(cus_id))
    }

    @GetMapping("items")
    fun getAllItems(@RequestParam order_id: Long):ResponseEntity<Any>{
        orderService.getAllItems(dao.getOrder(order_id))?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(orderService.getAllItems(dao.getOrder(order_id)))
    }

    @PostMapping("add-items")
    fun addItems(@RequestBody orderRequest: OrderRequest):ResponseEntity<Any>{
        if(orderService.addMoreItems(dao.getOrder(orderRequest.order_id), dao.getAllProductQuantity(orderRequest.pro_ids_quantities)))
            return ResponseEntity.accepted().build()
        return ResponseEntity.badRequest().build()
    }

    @PutMapping("update-item")
    fun updateItems(@RequestBody orderRequest: OrderRequest):ResponseEntity<Any>{
        return if (orderService.updateOrderItems(dao.getOrder(orderRequest.order_id),
                    dao.getAllProductQuantity(orderRequest.pro_ids_quantities)))
            ResponseEntity.accepted().build()
        else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("remove-order")
    fun removeOrder(@RequestParam order_id:Long):ResponseEntity<Any>{
        return if (orderService.removeOrder(dao.getOrder(order_id)))
            ResponseEntity.accepted().build()
        else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("remove-item")
    fun removeItem(@RequestParam item_id:Long):ResponseEntity<Any>{
        if(orderService.removeOrderItem(dao.getOrderByOrderDetailId(item_id), item_id))
            return ResponseEntity.accepted().build()
        return ResponseEntity.badRequest().build()

    }
}