package com.creditas.shop.domain.dao

import com.creditas.shop.domain.entities.Users
import org.springframework.data.repository.CrudRepository
import java.util.*

interface IUserDao: CrudRepository <Users, Int> {
    public fun findByEmail(email:String): Optional<Users>
    public fun findByEmailAndPassword(email: String,password: String): Optional<Users>;
}