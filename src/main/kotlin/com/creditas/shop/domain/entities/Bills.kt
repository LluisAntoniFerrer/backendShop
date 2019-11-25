package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sun.istack.NotNull
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "bill")
data class Bills (
        @Id
        @GeneratedValue
        var id: Int?,
        @NotNull
        @JsonBackReference(value="user_bill")
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="users_id")
        var user: Users?,
        @NotNull
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinTable(name = "bill_product_stock",
                joinColumns = [JoinColumn(name = "bills_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "product_stock_id", referencedColumnName = "id")])
        @JsonManagedReference(value="bill_product_stock")
        var products_stocks: List<ProductStock>?,
        @NotNull
        var create_at: LocalDateTime? = LocalDateTime.now(),
        var update_at: LocalDateTime?
)