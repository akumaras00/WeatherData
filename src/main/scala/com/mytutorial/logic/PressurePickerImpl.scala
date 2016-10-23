package com.mytutorial.logic

import scala.math._

import com.mytutorial.model.Location
/**
 * Calculates the atmospheric pressure based on given temperature and location
 */
class PressurePickerImpl extends PressurePicker {
  
  val GRAVITATIONAL_ACCELERATION  = 9.80665
  val MOLAR_MASS_OF_EARTH_AIR = 0.0289644 //kg/mol
  val UNIVERSAL_GAS_CONSTANT = 8.31432 
  val BOTTOM_PRESSURE = 22632.0 //in pa
  val BOTTOM_ALTITUDE = 11000.00 //in meters
  val TEMPERATURE_LAPSE = -0.0065  // Kelvin/m
  val STRATOSPHERE_TEMP_CONST = 71.5
  
  def getPressure(givenTemperature: Double, location: Location) : Double = {
    val givenTempInKelvin = givenTemperature + 273.15
    val givenAltitude = location.elevation
    
    val numerator = - GRAVITATIONAL_ACCELERATION * MOLAR_MASS_OF_EARTH_AIR * (givenAltitude - BOTTOM_ALTITUDE) 
    val denominator = UNIVERSAL_GAS_CONSTANT * (givenTempInKelvin - STRATOSPHERE_TEMP_CONST)
    
    math.round((BOTTOM_PRESSURE * exp(numerator/denominator))/100) // in hPa
    
  }
}