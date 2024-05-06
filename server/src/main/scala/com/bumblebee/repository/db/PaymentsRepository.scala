package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Payments, PaymentsCreate}
import org.springframework.stereotype.Component
import scalikejdbc.{scalikejdbcSQLInterpolationImplicitDef, DBSession, SQLSyntax, SQLUtil, WrappedResultSet}

import java.sql.Date

@Component
class PaymentsRepository {

  def insert(entity: PaymentsCreate)(implicit session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | INSERT INTO
            |   payments (
            |     user_id,
            |     amount,
            |     fee,
            |     fee_rate,
            |     tax_rate,
            |     billing_amount,
            |     transfer_date,
            |     uploaded_date,
            |     status
            |   ) VALUES (
            |     ${entity.userId},
            |     ${entity.amount},
            |     ${entity.fee},
            |     ${entity.feeRate},
            |     ${entity.taxRate},
            |     ${entity.billingAmount},
            |     ${entity.transferDate},
            |     ${entity.uploadedDate},
            |     ${entity.status}
            |   )
            |""".stripMargin
    SQLUtil.sql(sqlQuery).updateAndReturnGeneratedKey(1).apply
  }

  def get(id: Long)(implicit session: DBSession): Option[Payments] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM payments
            | WHERE id = $id
            |""".stripMargin
    SQLUtil
      .sql(sqlQuery)
      .map(mapEntity)
      .single
      .apply()
  }

  def getByTransferDate(
      startTransferDate: Date,
      endTransferDate: Date
  )(implicit session: DBSession): List[Payments] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM payments
            | WHERE transfer_date BETWEEN $startTransferDate AND $endTransferDate
            |""".stripMargin
    SQLUtil.sql(sqlQuery).map(mapEntity).list.apply()
  }

  def mapEntity(ws: WrappedResultSet): Payments =
    Payments(
      id = ws.long("id"),
      userId = ws.long("user_id"),
      amount = ws.int("amount"),
      fee = ws.int("fee"),
      feeRate = ws.bigDecimal("fee_rate"),
      taxRate = ws.bigDecimal("tax_rate"),
      billingAmount = ws.int("billing_amount"),
      transferDate = ws.date("transfer_date"),
      uploadedDate = ws.date("uploaded_date"),
      status = ws.int("status"),
      createdAt = ws.timestamp("created_at"),
      updatedAt = ws.timestamp("updated_at")
    )
}
