package com.bumblebee.repository.db

import com.bumblebee.model.entity.{BankBranches, BankBranchesCreate}
import org.springframework.stereotype.Component
import scalikejdbc.{scalikejdbcSQLInterpolationImplicitDef, DBSession, SQLSyntax, SQLUtil, WrappedResultSet}

@Component
class BankBranchesRepository {

  def insert(entity: BankBranchesCreate)(implicit session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | INSERT INTO
            |   bank_branches (
            |     bank_code,
            |     branch_code,
            |     branch_name
            |   ) VALUES (
            |     ${entity.bankCode},
            |     ${entity.branchCode},
            |     ${entity.branchName}
            |   )
            |""".stripMargin
    SQLUtil.sql(sqlQuery).updateAndReturnGeneratedKey(1).apply
  }

  def get(id: Long)(implicit session: DBSession): Option[BankBranches] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM bank_branches
            | WHERE id = $id
            |""".stripMargin
    SQLUtil
      .sql(sqlQuery)
      .map(mapEntity)
      .single
      .apply()
  }

  def mapEntity(ws: WrappedResultSet): BankBranches =
    BankBranches(
      id = ws.long("id"),
      bankCode = ws.string("bank_code"),
      branchCode = ws.string("branch_code"),
      branchName = ws.string("branch_name"),
      createdAt = ws.timestamp("created_at"),
      updatedAt = ws.timestamp("updated_at")
    )
}
