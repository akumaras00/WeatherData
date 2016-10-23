package com.mytutorial.logic

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants.APRIL
import org.joda.time.DateTimeConstants.AUGUST
import org.joda.time.DateTimeConstants.DECEMBER
import org.joda.time.DateTimeConstants.FEBRUARY
import org.joda.time.DateTimeConstants.JANUARY
import org.joda.time.DateTimeConstants.JULY
import org.joda.time.DateTimeConstants.JUNE
import org.joda.time.DateTimeConstants.MARCH
import org.joda.time.DateTimeConstants.MAY
import org.joda.time.DateTimeConstants.NOVEMBER
import org.joda.time.DateTimeConstants.OCTOBER
import org.joda.time.DateTimeConstants.SEPTEMBER

import com.mytutorial.loader.WeatherDataLoader
import com.mytutorial.model.AvgTemperatureLearningData
import com.mytutorial.model.Location

/**
 * Estimates the temperature based on variables like given time of the day, season, latitude, altitude, etc
 * It reads the area average from a file and then adjusts the temperature based on above variables
 */
class TemperaturePickerImpl extends TemperaturePicker {

  import Season._

  lazy val TEMP_DATA_BY_LOCATION: Map[Location, AvgTemperatureLearningData] =
    WeatherDataLoader.loadTempTrainingData.map(row => (row.location, row)).toMap

  val MAX_LATITUDE = 90

  def getEstimatedTemperature(givenDateTime: DateTime, location: Location): Double = {
    val avgTemp = getDefaultTemperature(givenDateTime, location)
    val delta = getDeltaTemperature(givenDateTime, location)
    avgTemp + delta
  }

  private[this] def getDefaultTemperature(givenDateTime: DateTime, location: Location): Double = {
    val season = getSeason(givenDateTime, location);
    val tempDataByLocation = TEMP_DATA_BY_LOCATION(location)

    season match {
      case SPRING => tempDataByLocation.springAvgTemp
      case SUMMER => tempDataByLocation.summerAvgTemp
      case AUTUMN => tempDataByLocation.autumnAvgTemp
      case WINTER => tempDataByLocation.winterAvgTemp
    }
  }

  private[this] def getDeltaTemperature(givenDateTime: DateTime, location: Location): Double = {
    val res1 = getDeltaTemperatureByTimeOfTheDay(givenDateTime, location)
    val res2 = getDeltaTemperatureByLatitude(givenDateTime, location)
    val res3 = getDeltaTemperatureByElevation(location)
    res1 + res2 + res3
  }

  private[this] def getDeltaTemperatureByTimeOfTheDay(givenDateTime: DateTime, location: Location): Double = {
    val season = getSeason(givenDateTime, location);
    val hourOfADay = givenDateTime.getHourOfDay;
    hourOfADay match {
      case 0 | 1 | 2 | 3 | 4 | 21 | 22 | 23 => if (season == WINTER) -4.0
      else if (season == SUMMER)  -8
      else -1
      case 11 | 12 | 13 | 14 => if (season == SUMMER) 2.0
      else 0.0
      case _ => 0.0
    }
  }

  private[this] def getDeltaTemperatureByLatitude(givenDateTime: DateTime, location: Location): Double = {
    val season = getSeason(givenDateTime, location);
    val absLatitude = math.abs(location.latitude)

    season match {
      case SUMMER => math.round((MAX_LATITUDE - absLatitude) / 20)
      case WINTER => -math.round((MAX_LATITUDE - absLatitude) / 20)
      case _ => 0.0
    }
  }

  private[this] def getDeltaTemperatureByElevation(location: Location): Double = {
    val res = -math.round(location.elevation / 20)
    res
  }

  private[this] def getSeason(givenDateTime: DateTime, location: Location): Season = {
    givenDateTime.getMonthOfYear match {
      case NOVEMBER | DECEMBER | JANUARY => if (location.latitude < 0.0) SUMMER else WINTER
      case FEBRUARY | MARCH | APRIL => if (location.latitude < 0) AUTUMN else SPRING
      case MAY | JUNE | JULY => if (location.latitude < 0.0) WINTER else SUMMER
      case AUGUST | SEPTEMBER | OCTOBER => if (location.latitude < 0.0) SPRING else AUTUMN
    }
  }
}

object Season extends Enumeration {
  type Season = Value

  val SPRING, SUMMER, AUTUMN, WINTER = Value
}