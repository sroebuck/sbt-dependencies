package com.proinnovate.logback

/*
 * Copyright (c) Stuart Roebuck, 2011
 */

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Extra converter for Logback to enable colourisation of ERROR and WARN messages.
 */
class LogbackColorLevelConverter extends ClassicConverter {

  override def convert(event: ILoggingEvent): String = {
    val sbuf = new StringBuffer
    sbuf.append(event.getLevel.toInt match {
      case Level.ERROR_INT ⇒ LogbackColours.ERROR_BACKGROUND_COLOR
      case Level.WARN_INT  ⇒ LogbackColours.WARN_BACKGROUND_COLOR
      case _               ⇒ ""
    })
    sbuf.append(event.getLevel.toString.substring(0, 3))
    sbuf.append(event.getLevel.toInt match {
      case Level.ERROR_INT | Level.WARN_INT ⇒ LogbackColours.END_COLOR
      case _                                ⇒ ""
    })
    sbuf.toString
  }

}

class LogbackColorLevelStartConverter extends ClassicConverter {

  override def convert(event: ILoggingEvent): String = {
    event.getLevel.toInt match {
      case Level.DEBUG_INT ⇒ LogbackColours.DEBUG_COLOR
      case Level.INFO_INT  ⇒ LogbackColours.INFO_COLOR
      case Level.WARN_INT  ⇒ LogbackColours.WARN_COLOR
      case Level.ERROR_INT ⇒ LogbackColours.ERROR_COLOR
      case _               ⇒ ""
    }
  }

}

class LogbackColorLevelEndConverter extends ClassicConverter {

  override def convert(event: ILoggingEvent): String = LogbackColours.END_COLOR

}

object LogbackColours {

  final val END_COLOR = "\u001b[m"
  final val DEBUG_COLOR = "\u001b[38;5;15m"
  final val INFO_COLOR = "\u001b[m"
  final val ERROR_COLOR = "\u001b[0;31m"
  final val WARN_COLOR = "\u001b[0;33m"
  final val ERROR_BACKGROUND_COLOR = "\u001b[0;30;41m"
  final val WARN_BACKGROUND_COLOR = "\u001b[0;30;43m"

}
