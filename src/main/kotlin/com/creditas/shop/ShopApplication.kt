package com.creditas.shop

import com.creditas.shop.security.JWTAuthorizationFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@SpringBootApplication
class ShopApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ShopApplication>(*args)
        }
    }
    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig : WebSecurityConfigurerAdapter() {
        override fun configure(httpSecurity: HttpSecurity){
            httpSecurity
                    .cors()
                    .and()
                    .csrf().disable()
                    .antMatcher("/api/v1/bill/**").authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        }
    }
}