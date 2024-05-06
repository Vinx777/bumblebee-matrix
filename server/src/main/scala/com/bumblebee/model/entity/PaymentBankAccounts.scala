package com.bumblebee.model.entity

import java.sql.Timestamp
import scala.beans.BeanProperty

case class PaymentBankAccounts(
    @BeanProperty id: Long,
    @BeanProperty paymentId: Long,
    @BeanProperty bankCode: String,
    @BeanProperty bankName: String,
    @BeanProperty branchCode: String,
    @BeanProperty branchName: String,
    @BeanProperty accountType: Int,
    @BeanProperty accountNumber: String,
    @BeanProperty accountHolder: String,
    @BeanProperty createdAt: Timestamp,
    @BeanProperty updatedAt: Timestamp
)

case class PaymentBankAccountsCreate(
    @BeanProperty paymentId: Long,
    @BeanProperty bankCode: String,
    @BeanProperty bankName: String,
    @BeanProperty branchCode: String,
    @BeanProperty branchName: String,
    @BeanProperty accountType: Int,
    @BeanProperty accountNumber: String,
    @BeanProperty accountHolder: String
)
