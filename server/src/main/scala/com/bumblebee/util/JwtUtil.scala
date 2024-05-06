package com.bumblebee.util

import com.bumblebee.exception.AuthJWTException

import java.util.Base64

case class JwtUserInfo(
    email: String
)

object JwtUtil extends CommonLogging {

  def getJwtUserInfoFromToken(token: String): JwtUserInfo = {
    val jwtUserInfo: JwtUserInfo =
      try {
        val tokenChunks: Array[String] = token.replaceFirst("Bearer ", "").split("\\.")

        val urlDecoder: Base64.Decoder = Base64.getUrlDecoder
        val payload: String = new String(urlDecoder.decode(tokenChunks(1)))
        JsonUtil.fromJSON[JwtUserInfo](payload)
      }
      catch {
        case ex: Exception =>
          logger.error("Invalid token", ex)
          throw new AuthJWTException("Invalid token")
      }

    jwtUserInfo
  }
}
