package com.example.customer_order_product.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Transient

@Entity
class Staff(
    var name:String,
    var salary:Double,
    var phone:String?=null,
    var dob:String,
    @Transient
    var age:String = Period.between(LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.now()).toString(),
    @JsonIgnore
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
    var payments: MutableList<Payment>?=null
):Base()