package com.creditas.shop.domain.services

import com.creditas.shop.domain.entities.Users
import java.util.*
import javax.servlet.http.HttpServletRequest

interface IUserService {
    fun getUsers():List<Users>
    fun addUser(user:Users): Users
    fun getUserByID(id: Int): Optional<Users>
    fun getUserByEmail(email:String):Optional <Users>
    fun getJWT(email:String,id:Int,request:HttpServletRequest):String
    fun login(email:String,password:String):Optional<Users>
}