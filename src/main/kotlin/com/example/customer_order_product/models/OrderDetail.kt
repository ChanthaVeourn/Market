package com.example.customer_order_product.models

import java.math.BigDecimal
import javax.persistence.*

@Entity
class OrderDetail(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(name = "fk_order_id"))
    var order: Order,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", foreignKey =  ForeignKey(name = "fk_product_id"))
    var product:Product,

    var unitPrice:BigDecimal,

    var quantity:Int,

    var total:BigDecimal
):Base()