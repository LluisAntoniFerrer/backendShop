package com.creditas.shop.domain.services.implementation

import com.creditas.shop.domain.dao.IProductStockDao
import com.creditas.shop.domain.entities.ProductStock
import com.creditas.shop.domain.services.IProductStockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductStockServiceImpl: IProductStockService {

    @Autowired
    private lateinit var productStockDao: IProductStockDao

    override fun getProductStockByID(id: Int): Optional<ProductStock> = productStockDao.findById(id)
    override fun getProductsStock(page:Int,short:String):Page<ProductStock>{
        val pages: Pageable = PageRequest.of(page, 5, Sort.by(short).descending());
        return productStockDao.findAll(pages) as Page<ProductStock>;
    }
    override fun addProductStock(productStock: ProductStock): ProductStock = productStockDao.save(productStock);
    override fun getProductByType(type: Int): List<ProductStock> = productStockDao.findStockProducts(type);
    override fun changeBuyProduct(productStock: ProductStock): ProductStock {
        productStock.stock = productStock.stock -1;
        productStock.sales = productStock.sales +1;
        return productStockDao.save(productStock)
    }
}