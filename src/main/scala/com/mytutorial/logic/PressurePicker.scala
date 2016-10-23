package com.mytutorial.logic

import com.mytutorial.model.Location

trait PressurePicker {
   def getPressure(givenTemperature: Double, location: Location) : Double
}