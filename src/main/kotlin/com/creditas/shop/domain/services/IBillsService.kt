package com.creditas.shop.domain.services

import com.creditas.shop.domain.entities.Bills
import java.util.*

interface IBillsService {
    fun getBillByID(id: Int): Optional<Bills>
    fun getBills(): List<Bills>
    fun addBill(Bill:Bills): Bills

}