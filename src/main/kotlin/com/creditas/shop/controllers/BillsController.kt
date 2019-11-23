package com.creditas.shop.controllers

import com.creditas.shop.domain.entities.Bills
import com.creditas.shop.domain.services.IBillsService
import com.creditas.shop.domain.services.IProductStockService
import com.creditas.shop.domain.services.IProductsService
import com.creditas.shop.domain.services.IUserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*
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
    private lateinit var productsService: IProductsService;

    @Autowired
    private lateinit var productStockService: IProductStockService;


   /* @GetMapping("/bill/{id}")
    fun getBillByID(@PathVariable id: Int): ResponseEntity<Bills> {
        val result: Optional<Bills> = billsService.getBillByID(id)
        return result.map {res-> ResponseEntity(res, HttpStatus.OK)}
                .orElse(ResponseEntity(HttpStatus.NO_CONTENT))
    }
    @GetMapping("/bill")
    fun getBill(): ResponseEntity<List<Bills>> {
        var result:List<Bills> = billsService.getBills()
        return ResponseEntity(result,HttpStatus.OK);
    }
    @PostMapping("/bill")
    fun addUser(@RequestBody bill: Bills):ResponseEntity<Bills>{
        val result:Bills = billsService.addBill(bill)
        return ResponseEntity(result, HttpStatus.OK);
    }*/

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/buy/{productId}")
    fun createBill(request: HttpServletRequest,@PathVariable productId: Int): ResponseEntity<Bills>{
        val bill = Bills(null,null,null, LocalDateTime.now(),null);
       var jwtToken:String =  request.getHeader("Authorization").replace("Bearer"," ")
       var claims: Claims = Jwts.parser().setSigningKey("EstoEsPassword".toByteArray())
               .parseClaimsJws(jwtToken).body
        try {
            userService.getUserByID(claims.get("id") as Int).map { res -> bill.user = res }
                    .orElseThrow { Exception("User Not Found") }
        }catch(e:Exception){
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        try {
            productStockService.getProductStockByID(productId).map { res->
                val product = productStockService.changeBuyProduct(res)
                bill.products_stock= product
            }
                    .orElseThrow { Exception("product Not Found") }
        }catch(e:Exception){
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }

        val result:Bills = billsService.addBill(bill)
        return ResponseEntity(result, HttpStatus.OK);
    }
}