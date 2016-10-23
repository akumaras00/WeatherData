package com.mytutorial.logic

import com.mytutorial.loader.WeatherDataLoader
import com.mytutorial.model.AvgTemperatureLearningData
import com.mytutorial.model.Location
import com.mytutorial.model.AvgDewPointTemp
import org.joda.time.DateTime
/**
 * Calculates Atmospheric Humidity for a given location by temperature and dew point temperature
 */
class HumidityPickerImpl extends HumidityPicker {

  val DEWPOINT_TEMP_DATA_BY_LOCATION: Map[Location, AvgDewPointTemp] =
    WeatherDataLoader.loadDewPointTempTrainingData.map(row => (row.location, row)).toMap

  def getHumidity(location: Location, temperature: Double, givenDateTime: DateTime): Double = {
    var dewPointTemp = getDewPointTemperature(location, givenDateTime)
    
    if(dewPointTemp > temperature){
      dewPointTemp = temperature - 2
    }

    val numerator = math.exp((17.625 * dewPointTemp)/(243.04 + dewPointTemp))
    val denominator = math.exp((17.625  * temperature)/ (243.04 + temperature))
    
    math.round((numerator/denominator) * 100)
  }

  private[this] def getDewPointTemperature(location: Location, givenDateTime: DateTime): Double = {
    val dewPointTempData = DEWPOINT_TEMP_DATA_BY_LOCATION(location)

    givenDateTime.getMonthOfYear match {
      case 1 => dewPointTempData.dpTempJan
      case 2 => dewPointTempData.dpTempFeb
      case 3 => dewPointTempData.dpTempMarch
      case 4 => dewPointTempData.dpTempApril
      case 5 => dewPointTempData.dpTempMay
      case 6 => dewPointTempData.dpTempJune
      case 7 => dewPointTempData.dpTempJuly
      case 8 => dewPointTempData.dpTempAug
      case 9 => dewPointTempData.dpTempSept
      case 10 => dewPointTempData.dpTempOct
      case 11 => dewPointTempData.dpTempNov
      case 12 => dewPointTempData.dpTempDec
    }
  }
}