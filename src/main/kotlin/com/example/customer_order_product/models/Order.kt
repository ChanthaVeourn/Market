package com.example.customer_order_product.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "\"order\"")
class Order(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cusId", foreignKey = ForeignKey(name = "fk_cusId"), referencedColumnName = "id",)
    var customer:Customer,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_staff_id"))
    var staff: Staff,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true, cascade = [CascadeType.ALL])
    var orderDetails: MutableList<OrderDetail>? = mutableListOf(),

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true, cascade = [CascadeType.ALL])
    var invoiceDetails:MutableList<InvoiceDetail>? = mutableListOf(),

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true, cascade = [CascadeType.ALL])
    var payments: MutableList<Payment>?=null,

    var totalAmount:Double? = null,

    var paidStatus:Boolean = false
):Base()