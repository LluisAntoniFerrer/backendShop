package com.creditas.shop.controllers

import com.creditas.shop.domain.entities.ProductStock
import com.creditas.shop.domain.entities.Products
import com.creditas.shop.domain.entities.ProductsType
import com.creditas.shop.domain.services.IProductStockService
import com.creditas.shop.domain.services.IProductTypeService
import com.creditas.shop.domain.services.IProductsService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("api/v1/product")
class ProductsController {
    private var logger = LogFactory.getLog("ProductsController.class");
    @Autowired
    private lateinit var productsStockService: IProductStockService
    @Autowired
    private lateinit var productsService: IProductsService;
    @Autowired
    private lateinit var productsTypeService: IProductTypeService;

    @GetMapping("/all")
    fun getProducts(@RequestParam(defaultValue="0") page: Int,@RequestParam(defaultValue="sales") short: String): ResponseEntity<Page<ProductStock>> {
        var result:Page<ProductStock> = productsStockService.getProductsStock(page,short)
        return ResponseEntity(result,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    fun getProductsStockByProduct(@PathVariable id: Int): ResponseEntity<List<ProductStock>> {
        var result:List<ProductStock> = productsStockService.getProductByType(id)
        return ResponseEntity(result,HttpStatus.OK);
    }
    @GetMapping("/types")
    fun getProductTypes(): ResponseEntity<List<ProductsType>> {
        var result:List<ProductsType> = productsTypeService.getProductsType()
        return ResponseEntity(result,HttpStatus.OK);
    }
    @GetMapping("/type/{typeId}")
    fun getProductByType(@PathVariable typeId: Int,@RequestParam(defaultValue="0") page: Int): ResponseEntity<Page<Products>> {
        var result:Page<Products> = productsService.getProductsByType(typeId, page)
        return ResponseEntity(result,HttpStatus.OK);
    }
    @GetMapping("/bestseller")
    fun getProductBestSeller(@RequestParam(defaultValue="0") page: Int): ResponseEntity<Page<Products>> {
        var result:Page<Products> = productsService.getPoductsBestSeller(page)
        return ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("/search/{text}")
    fun seachProducts(@PathVariable text: String,@RequestParam(defaultValue="0") page: Int): ResponseEntity<Page<Products>> {
        var result:Page<Products> = productsService.seachProducts(text,text,page)
        return ResponseEntity(result,HttpStatus.OK);
    }
}