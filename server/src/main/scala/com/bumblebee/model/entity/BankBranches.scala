package com.bumblebee.model.entity

import java.sql.Timestamp
import scala.beans.BeanProperty

case class BankBranches(
    @BeanProperty id: Long,
    @BeanProperty bankCode: String,
    @BeanProperty branchCode: String,
    @BeanProperty branchName: String,
    @BeanProperty createdAt: Timestamp,
    @BeanProperty updatedAt: Timestamp
)

case class BankBranchesCreate(
    @BeanProperty bankCode: String,
    @BeanProperty branchCode: String,
    @BeanProperty branchName: String,
)
