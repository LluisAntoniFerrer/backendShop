package com.creditas.shop.domain.services.implementation

import com.creditas.shop.domain.dao.IProductTypeDao
import com.creditas.shop.domain.entities.ProductsType
import com.creditas.shop.domain.services.IProductTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductTypeServiceImpl:IProductTypeService {
    @Autowired
    private lateinit var productTypeDao: IProductTypeDao

    override fun getProductTypeByID(id: Int): Optional<ProductsType> = productTypeDao.findById(id)
    override fun getProductsType():List<ProductsType> = productTypeDao.findAll() as List<ProductsType>;
}