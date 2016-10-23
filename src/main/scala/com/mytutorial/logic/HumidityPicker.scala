package com.mytutorial.logic

import org.joda.time.DateTime
import com.mytutorial.model.Location

trait HumidityPicker {
   def getHumidity(location: Location, temperature: Double, givenDateTime: DateTime): Double
}