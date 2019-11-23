package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "product_memories")
data class ProductMemory (
        @Id
        @GeneratedValue
        var id: Int?,
        @NotNull
        var size: String,
        @JsonBackReference(value="product_memory")
        @OneToMany(mappedBy = "product_memory",fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var productStock: List<ProductStock>?
)