package com.mytutorial.logic

import org.scalatest.BeforeAndAfter
import org.junit.runner.RunWith
import org.joda.time.DateTimeConstants._
import org.scalatest.junit.JUnitRunner
import com.mytutorial.model.SYDNEY
import org.joda.time.LocalDateTime
import org.joda.time.DateTime
import com.mytutorial.model._
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class tempPickerTest extends FunSuite with BeforeAndAfter {

  var tempPicker: TemperaturePicker = null;

  before {
    tempPicker = new TemperaturePickerImpl()
  }

  test("Calculated temperature by date test Summer in Sydney") {
    val givenLocalDateTime = new DateTime(2016, DECEMBER, 15, 12, 0, 0)

    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, SYDNEY) == 38)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, LONDON) == -1)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, NEWYORK) == 0)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, LOSANGELES) == -4)

    val givenLocalDateTime2 = new DateTime(2016, MAY, 15, 12, 0, 0)

    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime2, SYDNEY) == 14)
  }
  
  
   test("Calculated temperature by date test Summer in London") {
    val givenLocalDateTime = new DateTime(2016, JUNE, 15, 12, 0, 0)

    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, SYDNEY) == 14)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, LONDON) == 27)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, NEWYORK) == 29)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, LOSANGELES) == 27)

    val givenLocalDateTime2 = new DateTime(2016, MAY, 15, 12, 0, 0)

    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime2, SYDNEY) == 14)
  }
   
   
  
  test("Calculated temperature by date test Summer in Sydney Early Morning") {
    val givenLocalDateTime = new DateTime(2016, DECEMBER, 2, 2, 0, 0)

    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, SYDNEY) == 28)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, LONDON) == -5)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, NEWYORK) == -4)
    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime, LOSANGELES) == -8)

    val givenLocalDateTime2 = new DateTime(2016, MAY, 15, 12, 0, 0)

    assert(tempPicker.getEstimatedTemperature(givenLocalDateTime2, SYDNEY) == 14)
  }
   
}