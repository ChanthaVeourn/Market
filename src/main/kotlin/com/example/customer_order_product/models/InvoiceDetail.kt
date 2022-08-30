package com.example.customer_order_product.models

import javax.persistence.*

@Entity
class InvoiceDetail(

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "invoiceDetail", cascade = [CascadeType.ALL])
    var payment: Payment,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_staff_id"), referencedColumnName = "id")
    var staff: Staff,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_order_id"), referencedColumnName = "id")
    var order: Order,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_cus_id"), referencedColumnName = "id")
    var customer: Customer,

    var amount_to_pay:Double

):Base()