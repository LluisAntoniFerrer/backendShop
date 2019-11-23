package com.creditas.shop.domain.services

import com.creditas.shop.domain.entities.ProductsType
import java.util.*

interface IProductTypeService {
    fun getProductTypeByID (id: Int): Optional<ProductsType>
    fun getProductsType(): List<ProductsType>
}