package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Users, UsersCreate}
import org.springframework.stereotype.Component
import scalikejdbc.{scalikejdbcSQLInterpolationImplicitDef, DBSession, SQLSyntax, SQLUtil, WrappedResultSet}

@Component
class UsersRepository {

  def insert(entity: UsersCreate)(implicit session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | INSERT INTO
            |   users (
            |     organization_id,
            |     name,
            |     email,
            |     password,
            |     verified
            |   ) VALUES (
            |     ${entity.organizationId},
            |     ${entity.name},
            |     ${entity.email},
            |     ${entity.password},
            |     ${entity.verified}
            |   )
            |""".stripMargin
    SQLUtil.sql(sqlQuery).updateAndReturnGeneratedKey(1).apply
  }

  def get(id: Long)(implicit session: DBSession): Option[Users] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM users
            | WHERE id = $id
            |""".stripMargin
    SQLUtil
      .sql(sqlQuery)
      .map(mapEntity)
      .single
      .apply()
  }

  def getResult(
      pageIndex: Long,
      rowPerPage: Long
  )(implicit session: DBSession): List[Users] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM users
            | ORDER BY created_at DESC
            | LIMIT ${pageIndex * rowPerPage}, $rowPerPage
            |""".stripMargin
    SQLUtil.sql(sqlQuery).map(mapEntity).list.apply()
  }

  def getRowCount()(
      implicit
      session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   COUNT(id) as cnt
            | FROM users
            |""".stripMargin
    SQLUtil.sql(sqlQuery).map(rs => rs.long("cnt")).single.apply().getOrElse(0L)
  }

  def mapEntity(ws: WrappedResultSet): Users =
    Users(
      id = ws.long("id"),
      organizationId = ws.long("organization_id"),
      name = ws.string("name"),
      email = ws.string("email"),
      password = ws.string("password"),
      verified = ws.boolean("verified"),
      createdAt = ws.timestamp("created_at"),
      updatedAt = ws.timestamp("updated_at")
    )
}
