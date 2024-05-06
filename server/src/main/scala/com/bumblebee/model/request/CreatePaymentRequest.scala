package com.bumblebee.model.request

import java.sql.Date
import scala.beans.BeanProperty

case class CreatePaymentRequest(
    @BeanProperty userId: Long,
    @BeanProperty amount: Int,
    @BeanProperty fee: Int,
    @BeanProperty feeRate: BigDecimal,
    @BeanProperty taxRate: Int,
    @BeanProperty billingAmount: Int,
    @BeanProperty transferDate: Date,
    @BeanProperty uploadedDate: Date,
    @BeanProperty status: Int,
    @BeanProperty bankCode: String,
    @BeanProperty bankName: String,
    @BeanProperty branchCode: String,
    @BeanProperty branchName: String,
    @BeanProperty accountType: Int,
    @BeanProperty accountNumber: String,
    @BeanProperty accountHolder: String
)
