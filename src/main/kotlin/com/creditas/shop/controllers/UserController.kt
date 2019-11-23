package com.creditas.shop.controllers

import com.creditas.shop.security.JWTAuthorizationFilter
import com.creditas.shop.domain.entities.Users
import com.creditas.shop.domain.services.IUserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("api/v1")
class UserController {


    private var Logger = LogFactory.getLog("UserController.class")

    @Autowired
    private lateinit var userService : IUserService;

    private lateinit var JWT : JWTAuthorizationFilter;

    @RequestMapping("/user","GET","application/json")
    fun getUsers(): ResponseEntity<List<Users>> {
        val list:List<Users> = userService.getUsers()
        return ResponseEntity(list, HttpStatus.OK)
    }

    @PostMapping("/user")
    fun addUser(@RequestBody user: Users):ResponseEntity<Users>{
        val result:Users = userService.addUser(user)
        return ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/bill")
    fun getBillsFromUsers(request: HttpServletRequest): ResponseEntity<Optional<Users>>{
        var jwtToken:String =  request.getHeader("Authorization").replace("Bearer"," ")
        var claims: Claims = Jwts.parser().setSigningKey("EstoEsPassword".toByteArray())
                .parseClaimsJws(jwtToken).body
        val result:Optional<Users> = userService.getUserByID(claims.get("id") as Int)
        return ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    fun login(@RequestBody user: Users, request: HttpServletRequest): ResponseEntity<String>{
        var result = userService.login(user.email,user.password )
        if(!result.isPresent) return ResponseEntity("Datos Incorrecto", HttpStatus.BAD_REQUEST)
        var token:String = userService.getJWT(result.get().email as String,result.get().id as Int, request)
        return ResponseEntity(token, HttpStatus.OK);
    }
}