package com.bumblebee.util

import org.slf4j.{Logger, LoggerFactory}

trait CommonLogging {
  protected val logger: Logger = LoggerFactory.getLogger(this.getClass)
}
