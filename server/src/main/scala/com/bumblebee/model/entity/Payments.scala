package com.bumblebee.model.entity

import java.sql.{Date, Timestamp}
import scala.beans.BeanProperty

case class Payments(
    @BeanProperty id: Long,
    @BeanProperty userId: Long,
    @BeanProperty amount: Int,
    @BeanProperty fee: Int,
    @BeanProperty feeRate: BigDecimal,
    @BeanProperty taxRate: BigDecimal,
    @BeanProperty billingAmount: Int,
    @BeanProperty transferDate: Date,
    @BeanProperty uploadedDate: Date,
    @BeanProperty status: Int,
    @BeanProperty createdAt: Timestamp,
    @BeanProperty updatedAt: Timestamp
)

case class PaymentsCreate(
    @BeanProperty userId: Long,
    @BeanProperty amount: Int,
    @BeanProperty fee: Int,
    @BeanProperty feeRate: BigDecimal,
    @BeanProperty taxRate: BigDecimal,
    @BeanProperty billingAmount: Int,
    @BeanProperty transferDate: Date,
    @BeanProperty uploadedDate: Date,
    @BeanProperty status: Int
)
