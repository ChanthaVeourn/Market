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

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true, cascade = [CascadeType.ALL])
    var orderDetails: MutableList<OrderDetail>?=null,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true, cascade = [CascadeType.ALL])
    var invoices:MutableList<Invoice>?=null,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true, cascade = [CascadeType.ALL])
    var payment: Payment?=null
):Base()