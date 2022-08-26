package com.example.customer_order_product.models

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class InvoiceDetail(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_payment_id"), referencedColumnName = "id")
    var payment: Payment,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_invoice_id"), referencedColumnName = "id")
    var invoice: Invoice,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_staff_id"), referencedColumnName = "id")
    var staff: Staff,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_order_id"), referencedColumnName = "id")
    var order: Order,

):Base()