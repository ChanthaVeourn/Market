package com.example.customer_order_product.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.persistence.*

@MappedSuperclass
open class Base(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id:Long?=null,
    var createdDate: LocalDate = LocalDate.parse(
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy")),
        DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
    )
)