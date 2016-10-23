package com.mytutorial.logic

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants._
import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.mytutorial.model.LONDON
import com.mytutorial.model.NEWYORK
import com.mytutorial.model.SYDNEY
import com.mytutorial.model.LOSANGELES


@RunWith(classOf[JUnitRunner])
class HumidityPickerTest extends FunSuite with BeforeAndAfter {
   var humidityPicker: HumidityPicker = null
   
   before {
     humidityPicker = new HumidityPickerImpl()
   }
  
  test("Humidity picker test1") {
     val givenLocalDateTime = new DateTime(2016, OCTOBER , 15, 16, 0, 0)
    
    assert(humidityPicker.getHumidity(SYDNEY, 30, givenLocalDateTime) == 25)
    assert(humidityPicker.getHumidity(LONDON, 10, givenLocalDateTime) == 91)
    assert(humidityPicker.getHumidity(LOSANGELES, 10, givenLocalDateTime) == 93)
    assert(humidityPicker.getHumidity(NEWYORK, 10, givenLocalDateTime) == 89)
  }
  
}