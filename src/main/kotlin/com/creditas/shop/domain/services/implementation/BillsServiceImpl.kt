package com.creditas.shop.domain.services.implementation

import com.creditas.shop.domain.dao.IBillsDao
import com.creditas.shop.domain.entities.Bills
import com.creditas.shop.domain.services.IBillsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BillsServiceImpl: IBillsService{

    @Autowired
    private lateinit var billDao: IBillsDao
    override fun getBillByID(id: Int): Optional<Bills> = billDao.findById(id);
    override fun addBill(bill: Bills): Bills = billDao.save(bill);
    @Transactional(readOnly=true)
    override fun getBills():List<Bills> = billDao.findAll() as List<Bills>;


}