package com.mytutorial.app

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants._

@RunWith(classOf[JUnitRunner])
class WeatherDataMainIntegrationTest extends FunSuite with BeforeAndAfter {

  var wdMain = new WeatherDataMain()

  test("parsing the given input without args") {
    val args = Array[String]()
    val result: List[DateTime] = wdMain.parseInput(args)
    assert(result.size == 1)
  }

  test("parsing the given input with 1 arg") {
    val args = Array[String]("2017/06/01 2:00")
    val result: List[DateTime] = wdMain.parseInput(args)
    assert(result.size == 1)
  }

  test("parsing the given input with 2 args") {
    val args = Array[String]("2017/06/01 2:00", "2017/06/01 4:00")
    val result: List[DateTime] = wdMain.parseInput(args)
    assert(result.size == 1)

    val args2 = Array[String]("2017/06/01 2:00", "2017/06/02 4:00")
    val result2: List[DateTime] = wdMain.parseInput(args2)
    assert(result2.size == 7)
  }

  test("exception when incorrect date format is supplied") {
    val thrown = intercept[Exception] {
      val args = Array[String]("2017/06/01")
      val result: List[DateTime] = wdMain.parseInput(args)
    }
    assert(thrown.getMessage != null)
  }

  test("weather data reports for 1 given dateTime") {
    val givenLocalDateTime = new DateTime(2016, DECEMBER, 15, 12, 0, 0)
    val result = wdMain.simulateWeather(List(givenLocalDateTime))
    assert(result.size == 8)
    assert(result(0).formattedText == "SYDNEY|-33.86,151.21,19.0|2016-12-15T12:00:00.000+11:00|Sunny|38.0|1083.0|22.0")
    assert(result(1).formattedText == "MELBOURNE|-37.81,144.96,7.0|2016-12-15T12:00:00.000+11:00|Sunny|37.0|1092.0|23.0")
    assert(result(2).formattedText == "AUCKLAND|-36.8,174.96,79.0|2016-12-15T14:00:00.000+13:00|Sunny|31.0|1125.0|32.0")

    assert(result(3).formattedText == "LONDON|51.5,0.12,35.0|2016-12-15T01:00:00.000Z|Rain|-5.0|1521.0|86.0")
    assert(result(4).formattedText == "NEWYORK|40.7,-74.0,10.0|2016-12-14T20:00:00.000-05:00|Rain|0.0|1457.0|86.0")
    assert(result(5).formattedText == "LOSANGELES|34.05,-118.2,71.0|2016-12-14T17:00:00.000-08:00|Rain|-4.0|1497.0|86.0")
    assert(result(6).formattedText == "NEWDELHI|28.65,77.231,213.0|2016-12-15T06:30:00.000+05:30|Rain|1.0|1395.0|86.0")
    assert(result(7).formattedText == "TOKYO|35.68,139.69,56.0|2016-12-15T10:00:00.000+09:00|Sunny|9.0|1335.0|70.0")
  }

  test("weather data reports for 2 given dateTimes") {
    val givenLocalDateTime1 = new DateTime(2016, DECEMBER, 15, 12, 0, 0)
    val givenLocalDateTime2 = new DateTime(2016, DECEMBER, 15, 13, 0, 0)
    val result = wdMain.simulateWeather(List(givenLocalDateTime1, givenLocalDateTime2))
    assert(result.size == 16)
  }
}