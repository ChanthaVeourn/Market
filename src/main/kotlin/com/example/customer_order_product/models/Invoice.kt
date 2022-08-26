package com.example.customer_order_product.models

import javax.persistence.*

@Entity
class Invoice(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cusId", foreignKey = ForeignKey(name = "fk_cusId"), referencedColumnName = "id")
    var customer:Customer,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(name = "fk_orderId"), referencedColumnName = "id")
    var order:Order
):Base()