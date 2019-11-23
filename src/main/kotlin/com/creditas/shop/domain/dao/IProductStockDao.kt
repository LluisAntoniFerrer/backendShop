package com.creditas.shop.domain.dao

import com.creditas.shop.domain.entities.ProductStock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import java.awt.print.Pageable

interface IProductStockDao : PagingAndSortingRepository<ProductStock, Int> {
    fun findByProductEquals(id: Int): List<ProductStock>;

    @Query("SELECT * FROM shop.product_stock where product_id = ?1 ",nativeQuery = true)
    fun findStockProducts(id:Int) : List<ProductStock>

}