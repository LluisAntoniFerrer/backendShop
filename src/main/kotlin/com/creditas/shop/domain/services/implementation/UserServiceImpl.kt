package com.creditas.shop.domain.services.implementation

import com.creditas.shop.domain.dao.IUserDao
import com.creditas.shop.domain.entities.Users
import com.creditas.shop.domain.services.IUserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.catalina.User
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@Service
class UserServiceImpl  : IUserService {

    private var Logger = LogFactory.getLog("UserServiceImpl.class")
    @Autowired
    private lateinit var  userDao: IUserDao;

    override fun getUserByID(id: Int): Optional<Users> = userDao.findById(id);

    override fun addUser(user: Users): Users = userDao.save(user);

    @Transactional(readOnly=true)
    override fun getUsers(): List<Users> = userDao.findAll() as List<Users>;

    override fun getUserByEmail(email: String): Optional<Users> = userDao.findByEmail(email);

    override fun getJWT(email: String,id:Int,request:HttpServletRequest):String{
        lateinit var  secretKey:String
        var grantedAuthorities:List<GrantedAuthority> = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        var  token:String = Jwts
                .builder()
                .claim("ip", request.getRemoteAddr())
                .claim("id", id)
                .setId("Creditas")
                .setSubject(email)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 60000000))
                .signWith(SignatureAlgorithm.HS512, "EstoEsPassword".toByteArray()).compact()
        return "Bearer "+ token
    }
    override fun login(email: String, password: String): Optional<Users> = userDao.findByEmailAndPassword(email,password)


}