package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Banks, BanksCreate}
import org.springframework.stereotype.Component
import scalikejdbc.{scalikejdbcSQLInterpolationImplicitDef, DBSession, SQLSyntax, SQLUtil, WrappedResultSet}

@Component
class BanksRepository {

  def insert(entity: BanksCreate)(implicit session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | INSERT INTO
            |   banks (
            |     bank_code,
            |     bank_name
            |   ) VALUES (
            |     ${entity.bankCode},
            |     ${entity.bankName}
            |   )
            |""".stripMargin
    SQLUtil.sql(sqlQuery).updateAndReturnGeneratedKey(1).apply
  }

  def get(id: Long)(implicit session: DBSession): Option[Banks] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM banks
            | WHERE id = $id
            |""".stripMargin
    SQLUtil
      .sql(sqlQuery)
      .map(mapEntity)
      .single
      .apply()
  }

  def mapEntity(ws: WrappedResultSet): Banks =
    Banks(
      id = ws.long("id"),
      bankCode = ws.string("bank_code"),
      bankName = ws.string("bank_name"),
      createdAt = ws.timestamp("created_at"),
      updatedAt = ws.timestamp("updated_at")
    )
}
