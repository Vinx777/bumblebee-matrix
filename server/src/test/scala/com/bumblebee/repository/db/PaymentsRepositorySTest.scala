package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Payments, PaymentsCreate}
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scalikejdbc.DB

import java.sql.Date

class PaymentsRepositorySTest extends DBTestBase {

  @Resource
  val repository: PaymentsRepository = null

  ignore should "work as expected" in {
    val currentDate = new Date(System.currentTimeMillis())
    val create =
      PaymentsCreate(
        userId = 1L,
        amount = 10,
        fee = 0,
        feeRate = BigDecimal(0.00000),
        taxRate = BigDecimal(0.10000),
        billingAmount = 11,
        transferDate = currentDate,
        uploadedDate = currentDate,
        status = 1)

    DB.localTx { implicit session =>
      val id: Long = repository.insert(create)
      val maybePayments: Option[Payments] = repository.get(id)

      maybePayments.isDefined shouldBe true
      maybePayments.get shouldBe maybePayments.get.copy(
        userId = create.userId,
        amount = create.amount,
        fee = create.fee,
        feeRate = create.feeRate,
        taxRate = create.taxRate,
        billingAmount = create.billingAmount,
        transferDate = create.transferDate,
        uploadedDate = create.uploadedDate,
        status = create.status
      )
    }
  }
}
