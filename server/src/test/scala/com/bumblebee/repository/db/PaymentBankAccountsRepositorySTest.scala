package com.bumblebee.repository.db

import com.bumblebee.model.entity.{PaymentBankAccounts, PaymentBankAccountsCreate}
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scalikejdbc.DB

import java.sql.Date

class PaymentBankAccountsRepositorySTest extends DBTestBase {

  @Resource
  val repository: PaymentBankAccountsRepository = null

  "PaymentBankAccountsRepository" should "work as expected" in {
    val currentDate = new Date(System.currentTimeMillis())
    val create = PaymentBankAccountsCreate(
      paymentId = 1L,
      bankCode = "123",
      bankName = "123",
      branchCode = "123",
      branchName = "123",
      accountType = 1,
      accountNumber = "123",
      accountHolder = "123")

    DB.localTx { implicit session =>
      val id: Long = repository.insert(create)
      val maybePaymentBankAccounts: Option[PaymentBankAccounts] = repository.get(id)

      maybePaymentBankAccounts.isDefined shouldBe true
      maybePaymentBankAccounts.get shouldBe maybePaymentBankAccounts.get.copy(
        paymentId = create.paymentId,
        bankCode = create.bankCode,
        bankName = create.bankName,
        branchCode = create.branchCode,
        branchName = create.branchName,
        accountType = create.accountType,
        accountNumber = create.accountNumber,
        accountHolder = create.accountHolder
      )
    }
  }
}
