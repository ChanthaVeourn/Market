package com.example.customer_order_product

import com.example.customer_order_product.models.Customer
import com.example.customer_order_product.models.Order
import com.example.customer_order_product.models.Product
import com.example.customer_order_product.models.Staff
import com.example.customer_order_product.repo.CustomerRepo
import com.example.customer_order_product.repo.OrderRepo
import com.example.customer_order_product.repo.ProductRepo
import com.example.customer_order_product.repo.StaffRepo
import org.springframework.stereotype.Component

@Component
class DAO(private val productRepo: ProductRepo,
          private val customerRepo: CustomerRepo,
          private val staffRepo: StaffRepo,
          private val orderRepo: OrderRepo
)  {

    fun getProduct(pro_id:Long):Product?{
        return productRepo.findById(pro_id).orElse(null)
    }

    fun getAllProductQuantity(proIdsQuantities:Map<String, Int>):Map<Product?, Int>{
        val productsQuantities = mutableMapOf<Product?, Int>()
        proIdsQuantities.forEach{ (pro_id, quantity) ->
            productsQuantities[productRepo.findById(pro_id.toLong()).orElse(null)] = quantity
        }
        return productsQuantities
    }

    fun getStaff(staff_id:Long?): Staff?{
        staff_id?:return null
        return staffRepo.findById(staff_id).orElse(null)
    }

    fun getCustomer(cus_id:Long?): Customer?{
        cus_id?: return null
        return customerRepo.findById(cus_id).orElse(null)
    }

    fun getOrderByOrderDetailId(id:Long?): Order?{
        id?: return null
        return orderRepo.findByOrderDetailsId(id)
    }

    fun getOrder(id:Long?):Order?{
        id?: return null
        return orderRepo.findById(id).orElse(null)
    }
}