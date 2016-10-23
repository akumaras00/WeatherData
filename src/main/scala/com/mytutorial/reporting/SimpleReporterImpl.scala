package com.mytutorial.reporting

import org.joda.time.DateTime
import com.mytutorial.model.Location

/**
 *  Formats to the final Report
 */
class SimpleReporterImpl(location: Location, localTime: DateTime, temp: Double, pressure: Double, humidity: Double) extends Reporter {

  val PIPE_SEP = "|"
  val COMMA_SEP = ","

  def formattedText: String = {
    var weatherCondition = WEATHER_CONDITION.Sunny;

    if (humidity > 80) {
      weatherCondition = WEATHER_CONDITION.Rain
    } else if (temp <= 0.0) {
      weatherCondition = WEATHER_CONDITION.Snow
    }

    location.name + PIPE_SEP + location.latitude + COMMA_SEP + location.longitude + COMMA_SEP + location.elevation +
      PIPE_SEP + localTime + PIPE_SEP + weatherCondition + PIPE_SEP + temp + PIPE_SEP + pressure + PIPE_SEP + humidity
  }

  object WEATHER_CONDITION extends Enumeration {
    val Rain, Snow, Sunny = Value
  }

}