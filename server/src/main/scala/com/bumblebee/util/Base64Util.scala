package com.bumblebee.util

import java.nio.charset.StandardCharsets
import java.util.Base64

object Base64Util {

  def encode(string: String): String =
    new String(Base64.getEncoder.encode(string.getBytes), StandardCharsets.UTF_8)

  def decode(string: String): String =
    new String(Base64.getDecoder.decode(string), StandardCharsets.UTF_8)

  def decodeByte(string: String): Array[Byte] =
    Base64.getDecoder.decode(string)
}
