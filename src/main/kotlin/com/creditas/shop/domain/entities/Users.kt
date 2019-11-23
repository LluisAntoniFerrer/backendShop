package com.creditas.shop.domain.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
data class Users (
        @Id
        @GeneratedValue
        var id: Int?,
        @NotNull
        var name: String?,
        @NotNull
        @Column(unique=true)
        var email: String,
        @NotNull
        var password: String,
        @JsonManagedReference(value="user_bill")
        @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true)
        var bills: List<Bills>?,
        @NotNull
        @CreationTimestamp
        var create_at: LocalDateTime? = LocalDateTime.now(),
        @UpdateTimestamp
        var update_at: LocalDateTime?
)