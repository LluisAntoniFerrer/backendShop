package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.validation.constraints.NotNull
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "products")
data class Products (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int?,
        @NotNull
        var name: String?,
        @NotNull
        var brand: String?,
        @JsonManagedReference(value="product_type")
        @NotNull
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="product_type_id")
        var product_type: ProductsType?,
        @NotNull
        var image: String?,
        @JsonBackReference(value="product")
        @OneToMany(mappedBy = "product",fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var product_stock: List<ProductStock>?,
        @NotNull
        var create_at: LocalDateTime? = LocalDateTime.now(),
        var update_at: LocalDateTime?
)