package com.example.customer_order_product.models

import javax.persistence.*

@Entity
class OrderDetail(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(name = "fk_order_id"))
    var order: Order,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_staff_id"))
    var staff: Staff,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", foreignKey =  ForeignKey(name = "fk_product_id"))
    var product:Product,

    var unitPrice:Double? = product.unitPrice,
    var quantity:Int?,
    var total:Double? = unitPrice?.times(quantity!!)
):Base()