package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "product_type")
data class ProductsType(
        @Id
        @GeneratedValue
        var id: Int?,
        @NotNull
        var name: String,
        @JsonBackReference(value="product_type")
        @OneToMany(mappedBy = "product_type",fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var products: List<Products>?
)