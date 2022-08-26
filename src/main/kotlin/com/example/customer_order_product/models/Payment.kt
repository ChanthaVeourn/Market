package com.example.customer_order_product.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class Payment(
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cusId", foreignKey = ForeignKey(name = "fk_cusId"), referencedColumnName = "id")
    var customer:Customer,

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Order::class)
    @JoinColumn(name= "orderId", foreignKey = ForeignKey(name = "fk_orderId"), referencedColumnName = "id")
    var order:Order,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId", foreignKey = ForeignKey(name = "fk_staffId"), referencedColumnName = "id")
    var staff:Staff,
    //testing
    var payAmount: Double? = order.orderDetails?.let{
            order_details -> order_details.
            map{it.totalAmount}
            .reduce{ acc, amount-> acc!!.plus(amount!!)
            }
    }
):Base()