package com.mytutorial.logic

import org.scalatest.BeforeAndAfter
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.mytutorial.model.LONDON
import com.mytutorial.model.NEWYORK
import com.mytutorial.model.SYDNEY
import com.mytutorial.model.LOSANGELES


@RunWith(classOf[JUnitRunner])
class PressuePickerTest extends FunSuite with BeforeAndAfter {
  
   var pressurePicker: PressurePicker = null
   
   before {
    pressurePicker = new PressurePickerImpl()
   }
  
  test("Pressure test1") {
    assert(pressurePicker.getPressure(30.00, SYDNEY) == 1143)
    assert(pressurePicker.getPressure(30.00, LONDON) == 1140)
    assert(pressurePicker.getPressure(30.00, NEWYORK) == 1145)
    assert(pressurePicker.getPressure(30.00, LOSANGELES) == 1134) 
  }
  
}