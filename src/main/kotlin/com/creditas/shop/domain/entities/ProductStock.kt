package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "product_stock")
data class ProductStock(
        @Id
        @GeneratedValue
        var id: Int?,
        @JsonManagedReference(value="product")
        @NotNull
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="product_id")
        var product: Products?,
        @JsonManagedReference(value="product_color")
        @NotNull
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="product_color_id")
        var product_color: ProductColor,
        @JsonManagedReference(value="product_memory")
        @NotNull
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="product_memory_id")
        var product_memory: ProductMemory,
        @NotNull
        var image: String,
        @NotNull
        @Lob
        @Column(name="description",columnDefinition="TEXT")
        var description: String?,
        @NotNull
        var stock: Int,
        @NotNull
        var sales: Int,
        @NotNull
        var price: String?,
        @JsonBackReference(value="product_stock")
        @OneToMany(mappedBy = "products_stock",fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var bills: List<Bills>?,
        @NotNull
        var create_at: LocalDateTime? = LocalDateTime.now(),
        var update_at: LocalDateTime?
)