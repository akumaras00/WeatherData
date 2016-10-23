package com.mytutorial.logic

import org.joda.time.DateTime

import com.mytutorial.model.Location

trait TemperaturePicker {
  def getEstimatedTemperature(givenDateTime: DateTime, location: Location): Double 
}