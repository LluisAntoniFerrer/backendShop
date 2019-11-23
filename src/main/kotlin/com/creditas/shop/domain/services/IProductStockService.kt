package com.creditas.shop.domain.services

import com.creditas.shop.domain.entities.ProductStock
import org.springframework.data.domain.Page
import java.util.*

interface IProductStockService {
    fun getProductStockByID(id: Int): Optional<ProductStock>
    fun getProductsStock(page:Int,short:String): Page<ProductStock>
    fun addProductStock(product:ProductStock): ProductStock
    fun getProductByType(type: Int):List<ProductStock>
    fun changeBuyProduct(productStock: ProductStock): ProductStock;

}