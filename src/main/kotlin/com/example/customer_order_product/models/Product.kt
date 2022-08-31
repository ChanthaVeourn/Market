package com.example.customer_order_product.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import javax.persistence.*

@Entity
class Product(
    var name:String,

    @Column(precision = 10, scale = 2)
    var unitPrice:BigDecimal,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_category_id"), referencedColumnName = "id")
    var category: Category,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var orderDetail: MutableList<OrderDetail>? = null
):Base()