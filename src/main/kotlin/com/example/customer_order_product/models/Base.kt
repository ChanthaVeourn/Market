package com.example.customer_order_product.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.RandomAccess
import javax.persistence.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

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