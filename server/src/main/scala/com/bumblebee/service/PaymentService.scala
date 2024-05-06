package com.bumblebee.service

import com.bumblebee.model.entity.{PaymentBankAccountsCreate, Payments, PaymentsCreate}
import com.bumblebee.model.request.{CreatePaymentRequest, GetPaymentAmountRequest}
import com.bumblebee.model.response.{CommonEmptyResponse, GetIntResponse}
import com.bumblebee.repository.db._
import org.springframework.stereotype.Component
import scalikejdbc.DB

@Component
class PaymentService(
    private val paymentsRepository: PaymentsRepository,
    private val paymentBankAccountsRepository: PaymentBankAccountsRepository
) {

  def getPaymentAmount(req: GetPaymentAmountRequest): GetIntResponse = DB.readOnly { implicit session =>
    val payments: List[Payments] = paymentsRepository
      .getByTransferDate(startTransferDate = req.startTransferDate, endTransferDate = req.endTransferDate)
    val totalAmount: Int = payments.map(_.amount).sum

    GetIntResponse(info = Some(totalAmount), error = None)
  }

  def createPayment(req: CreatePaymentRequest): CommonEmptyResponse = DB.localTx { implicit session =>
    req.status match {
      case 0 =>
        val feeRate: BigDecimal = 0.04
        val taxRate: BigDecimal = 0.1
        val fee: Int = (req.amount * feeRate).toInt
        val tax: Int = (req.amount * taxRate).toInt
        val billingAmount: Int = req.amount + fee + tax

        val paymentsCreate: PaymentsCreate = PaymentsCreate(
          userId = req.userId,
          amount = req.amount,
          fee = fee,
          feeRate = feeRate,
          taxRate = taxRate,
          billingAmount = billingAmount,
          transferDate = req.transferDate,
          uploadedDate = req.uploadedDate,
          status = req.status
        )
        val paymentId: Long = paymentsRepository.insert(entity = paymentsCreate)

        val paymentBankAccountsCreate: PaymentBankAccountsCreate = PaymentBankAccountsCreate(
          paymentId = paymentId,
          bankCode = req.bankCode,
          bankName = req.bankName,
          branchCode = req.branchCode,
          branchName = req.branchName,
          accountType = req.accountType,
          accountNumber = req.accountNumber,
          accountHolder = req.accountHolder
        )

        paymentBankAccountsRepository.insert(paymentBankAccountsCreate)

      case _ =>
    }

    CommonEmptyResponse()
  }
}
