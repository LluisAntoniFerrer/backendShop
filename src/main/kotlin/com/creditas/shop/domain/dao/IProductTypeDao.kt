package com.creditas.shop.domain.dao

import com.creditas.shop.domain.entities.ProductsType
import org.springframework.data.repository.CrudRepository

interface IProductTypeDao: CrudRepository<ProductsType, Int> {

}