package com.example.customer_order_product.datastore

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FakeDataStore(){
    @Bean
    fun dataInit() = ApplicationRunner{

    }
}