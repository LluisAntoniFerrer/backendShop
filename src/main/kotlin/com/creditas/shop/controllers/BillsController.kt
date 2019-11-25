package com.creditas.shop.controllers

import com.creditas.shop.domain.entities.Bills
import com.creditas.shop.domain.entities.ProductStock
import com.creditas.shop.domain.services.IBillsService
import com.creditas.shop.domain.services.IProductStockService
import com.creditas.shop.domain.services.IUserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("api/v1/bill")
class BillsController {
    private var logger = LogFactory.getLog("BillsController.class");

    @Autowired
    private lateinit var billsService:IBillsService;

    @Autowired
    private lateinit var userService : IUserService;

    @Autowired
    private lateinit var productStockService: IProductStockService;

    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/buy")
    fun createBill(request: HttpServletRequest,@RequestBody products: List<ProductStock>): ResponseEntity<Bills>{
        val bill = Bills(1,null,null, LocalDateTime.now(),null);
       var jwtToken:String =  request.getHeader("Authorization").replace("Bearer"," ")
       var claims: Claims = Jwts.parser().setSigningKey("EstoEsPassword".toByteArray())
               .parseClaimsJws(jwtToken).body
        try {
            userService.getUserByID(claims.get("id") as Int).map { res -> bill.user = res }
                    .orElseThrow { Exception("User Not Found") }
        }catch(e:Exception){
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        val newProducts: MutableList<ProductStock> = mutableListOf();
        products.forEach{
            productStockService.changeBuyProduct(it)
        }
        bill.products_stocks = products;
        val result:Bills = billsService.addBill(bill)
        return ResponseEntity(result, HttpStatus.OK);
    }
}