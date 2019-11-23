package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "product_colors")
data class ProductColor(
        @Id
        @GeneratedValue
        var id: Int?,
        @NotNull
        var name: String,
        @JsonBackReference(value="product_color")
        @OneToMany(mappedBy = "product_color", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var productStock: List<ProductStock>?
)