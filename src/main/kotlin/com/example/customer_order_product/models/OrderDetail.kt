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

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(name = "orderDetailProduct",
        joinColumns = [JoinColumn(name = "orderDetailId", referencedColumnName = "id", foreignKey = ForeignKey(name = "fk_order_detail_id"))],
    inverseJoinColumns = [JoinColumn(name = "productId", referencedColumnName =  "id", foreignKey = ForeignKey(name = "fk_product_id"))],
        indexes = [Index(name = "idxOrderDetailId",columnList = "orderDetailId"), Index(name = "idx_Product_Id",columnList = "productId")],)
    var products:MutableList<Product>?=null,

    var totalAmount:Double? = products?.let{ it -> it.map { it.unitPrice }.reduce { acc, price -> acc + price }}
):Base()