package com.creditas.shop.domain.services.implementation

import com.creditas.shop.domain.dao.IProductDao
import com.creditas.shop.domain.entities.Products
import com.creditas.shop.domain.services.IProductsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductsServiceImpl: IProductsService {
    @Autowired
    private lateinit var productsDao: IProductDao

    override fun getProducts(): List<Products> = productsDao.findAll() as List<Products>;
    override fun getProductsByType(typeId:Int,page:Int): Page<Products> {
        val pages: Pageable = PageRequest.of(page, 5,Sort.by("sumSales").descending());
        return productsDao.findProductsByType(typeId,pages)
    }

    override fun getPoductsBestSeller(page: Int): Page<Products> {
        val pages: Pageable = PageRequest.of(page, 5,Sort.by("sumSales").descending());
        return productsDao.findBestSellers(pages)
    }

    override fun seachProducts(text: String,text1: String, page: Int): Page<Products> {
        val pages: Pageable = PageRequest.of(page, 5);
        return productsDao.findAllByBrandContainsOrNameContains(text,text1,pages);
    }

    override fun getProductByID(id: Int): Optional<Products>  = productsDao.findById(id)

}