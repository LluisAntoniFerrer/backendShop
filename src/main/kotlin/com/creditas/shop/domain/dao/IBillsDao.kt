package com.creditas.shop.domain.dao

import com.creditas.shop.domain.entities.Bills
import org.springframework.data.repository.CrudRepository

interface IBillsDao: CrudRepository<Bills, Int> {
}