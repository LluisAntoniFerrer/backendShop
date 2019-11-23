package com.creditas.shop.domain.services

import com.creditas.shop.domain.entities.Products
import org.springframework.data.domain.Page
import java.util.*

interface IProductsService {

    fun getProductByID(id: Int): Optional<Products>
    fun getProducts(): List<Products>
    fun getProductsByType(typeId:Int, page:Int): Page<Products>
    fun getPoductsBestSeller(page:Int):Page<Products>
    fun seachProducts(text:String,text1:String, page: Int):Page<Products>
}