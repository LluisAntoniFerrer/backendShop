package com.creditas.shop.domain.dao

import com.creditas.shop.domain.entities.Products
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository


interface IProductDao : PagingAndSortingRepository<Products, Int> {
    @Query("SELECT *,sum(product_stock.sales) AS sumSales FROM shop.products inner join shop.product_stock on products.id = product_stock.product_id " +
            "WHERE product_type_id = ?1 GROUP BY products.id",
            countQuery =  "SELECT count(products.id)  FROM shop.products inner join shop.product_stock on products.id = product_stock.product_id  WHERE product_type_id = ?1  GROUP BY products.id"
            ,nativeQuery = true)
    fun findProductsByType(id:Int,pageable:Pageable) : Page<Products>

    fun findAllByBrandContainsOrNameContains(text:String,text1:String,pageable: Pageable): Page<Products>;

    @Query(value= "SELECT *,sum(product_stock.sales) AS sumSales FROM shop.products inner join shop.product_stock on products.id = product_stock.product_id " +
            "GROUP BY products.id",
            countQuery =  "SELECT count(products.id)  FROM shop.products inner join shop.product_stock on products.id = product_stock.product_id GROUP BY products.id",
            nativeQuery = true )
    fun findBestSellers(pageable:Pageable) : Page<Products>
}