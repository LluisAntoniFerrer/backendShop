package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sun.istack.NotNull
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "bills")
data class Bills (
        @Id
        @GeneratedValue
        var bill_id: Int?,
        @NotNull
        @JsonBackReference(value="user_bill")
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="users_id")
        var user: Users?,
        @JsonManagedReference(value="product_stock")
        @NotNull
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="product_stock_id")
        var products_stock: ProductStock?,
        @NotNull
        var create_at: LocalDateTime? = LocalDateTime.now(),
        var update_at: LocalDateTime?
)