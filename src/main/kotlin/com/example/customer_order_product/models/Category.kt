package com.example.customer_order_product.models

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Category(
    var name:String,
    var description: String
):Base()