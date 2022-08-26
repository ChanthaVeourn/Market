package com.example.customer_order_product.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.jetbrains.annotations.NotNull
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity
class Customer(
    @NotNull
    var name:String,
    @Column(length = 16, nullable = true)
    var phone:String?=null,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "customer", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var orders: MutableList<Order>?=null,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "customer", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var invoices: MutableList<Invoice>?=null,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "customer", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var payments: MutableList<Payment>?=null
):Base()