package com.mytutorial.app

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import com.mytutorial.model.Location
import com.mytutorial.reporting.SimpleReporterImpl
import com.mytutorial.logic.PressurePicker
import com.mytutorial.logic.TemperaturePicker
import com.mytutorial.logic.HumidityPicker
import com.mytutorial.logic.HumidityPickerImpl
import com.mytutorial.logic.TemperaturePickerImpl
import com.mytutorial.logic.PressurePickerImpl
import com.mytutorial.util.TimeZoneConverter
import com.mytutorial.reporting.Reporter
import org.joda.time.Period
import com.mytutorial.model.SYDNEY

/**
 * The main runnable class that simulate weather and produces an output for
 * a given date time or a current date time
 */
object WeatherDataMain {

  val USAGE_TEXT =  """Usage:  com.mytutorial.app.WeatherDataMain ["fromDateTime" in yyyy/MM/dd HH:mm] ["toDateTime" in yyyy/MM/dd HH:mm]
  fromDateTime and toDateTime are optional. The examples are
                    
                    com.mytutorial.app.WeatherDataMain 
                    com.mytutorial.app.WeatherDataMain   "2017/06/15 15:00"
                    com.mytutorial.app.WeatherDataMain   "2017/06/15 15:00" "2018/06/15 15:00" """
  
  
  def main(args: Array[String]): Unit = {
    
    val wdMain = new WeatherDataMain();
    
    val listOfDateAndTimes = try {
      wdMain.parseInput(args)
    } catch {
      case e: Exception => println(e.getMessage);println(USAGE_TEXT); System.exit(1); Nil
    }

    val output: List[Reporter] = wdMain.simulateWeather(listOfDateAndTimes)
    output.foreach { x => println(x.formattedText) }

  }
}

class WeatherDataMain {
  
  def parseInput(args: Array[String]) : List[DateTime] = {
     val now = DateTime.now();
    
    val fromDateTime = args.length match {
      case 1 | 2 => DateTime.parse(args(0), DateTimeFormat.forPattern("yyyy/MM/dd HH:mm"))
      case _ => new DateTime()
    }

    val toDateTime = args.length match {
      case 2 => DateTime.parse(args(1), DateTimeFormat.forPattern("yyyy/MM/dd HH:mm"))
      case _ => null
    }

    if (fromDateTime != null && fromDateTime.compareTo(now) == -1 || (toDateTime != null && toDateTime.compareTo(now) == -1)) {
      throw new IllegalArgumentException("Can't emit data for dates in the past. Enter present or future dates")
    }

    if (toDateTime != null && toDateTime.compareTo(fromDateTime) == -1) {
      throw new IllegalArgumentException("to date must be after from date")
    }

    val step = new Period().withHours(4)

    val listOfDateAndTimes: List[DateTime] = toDateTime match {
      case null => List(fromDateTime)
      case _ => Iterator.iterate(fromDateTime)(_.plus(step)).takeWhile(!_.isAfter(toDateTime)).toList
    }
    
    listOfDateAndTimes
  }

  def simulateWeather(dates: List[DateTime]): List[Reporter] = {
    val tempPicker: TemperaturePicker = new TemperaturePickerImpl()
    val pressurePicker: PressurePicker = new PressurePickerImpl()
    val humidityPicker: HumidityPicker = new HumidityPickerImpl()

    val result: List[Reporter] = SYDNEY.getAllLocations.flatMap { x =>
      dates.map { date =>
        val localDateTime = TimeZoneConverter.localTimeToTargetLocation(date, x)
        val temp = tempPicker.getEstimatedTemperature(localDateTime, x)
        val pressure = pressurePicker.getPressure(temp, x)
        val humidity = humidityPicker.getHumidity(x, temp, localDateTime)

        val output: Reporter = new SimpleReporterImpl(x, localDateTime, temp, pressure, humidity)
        output
      }
    }
    result
  }

}