package com.example.customer_order_product.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class Product(
    var name:String,
    var unitPrice:Double,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_category_id"), referencedColumnName = "id")
    var category: Category,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var orderDetail: MutableList<OrderDetail>?=null
):Base()