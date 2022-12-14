package com.example.customer_order_product.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import javax.persistence.*

@Entity
class Payment(
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cusId", foreignKey = ForeignKey(name = "fk_cusId"), referencedColumnName = "id")
    var customer:Customer,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "orderId", foreignKey = ForeignKey(name = "fk_orderId"), referencedColumnName = "id")
    var order:Order,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId", foreignKey = ForeignKey(name = "fk_staffId"), referencedColumnName = "id")
    var staff:Staff,

    @JsonIgnoreProperties("hibernateLazyInitializer", "handler")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceDetailId", foreignKey = ForeignKey(name = "fk_invoiceDetail_id"), referencedColumnName = "id")
    var invoiceDetail: InvoiceDetail,

    var paidAmount:BigDecimal,
):Base()