package com.bumblebee.repository.db

import com.bumblebee.model.entity.{PaymentBankAccounts, PaymentBankAccountsCreate}
import org.springframework.stereotype.Component
import scalikejdbc.{scalikejdbcSQLInterpolationImplicitDef, DBSession, SQLSyntax, SQLUtil, WrappedResultSet}

@Component
class PaymentBankAccountsRepository {

  def insert(entity: PaymentBankAccountsCreate)(implicit session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | INSERT INTO
            |   payment_bank_accounts (
            |     payment_id,
            |     bank_code,
            |     bank_name,
            |     branch_code,
            |     branch_name,
            |     account_type,
            |     account_number,
            |     account_holder
            |   ) VALUES (
            |     ${entity.paymentId},
            |     ${entity.bankCode},
            |     ${entity.bankName},
            |     ${entity.branchCode},
            |     ${entity.branchCode},
            |     ${entity.accountType},
            |     ${entity.accountNumber},
            |     ${entity.accountHolder}
            |   )
            |""".stripMargin
    SQLUtil.sql(sqlQuery).updateAndReturnGeneratedKey(1).apply
  }

  def get(id: Long)(implicit session: DBSession): Option[PaymentBankAccounts] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM payment_bank_accounts
            | WHERE id = $id
            |""".stripMargin
    SQLUtil
      .sql(sqlQuery)
      .map(mapEntity)
      .single
      .apply()
  }

  def mapEntity(ws: WrappedResultSet): PaymentBankAccounts =
    PaymentBankAccounts(
      id = ws.long("id"),
      paymentId = ws.long("payment_id"),
      bankCode = ws.string("bank_code"),
      bankName = ws.string("bank_name"),
      branchCode = ws.string("branch_code"),
      branchName = ws.string("branch_name"),
      accountType = ws.int("account_type"),
      accountNumber = ws.string("account_number"),
      accountHolder = ws.string("account_holder"),
      createdAt = ws.timestamp("created_at"),
      updatedAt = ws.timestamp("updated_at")
    )
}
