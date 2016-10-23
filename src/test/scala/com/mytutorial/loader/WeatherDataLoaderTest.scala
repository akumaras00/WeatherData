package com.mytutorial.loader

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.mytutorial.model.SYDNEY
import com.mytutorial.model.TOKYO
import com.mytutorial.model.NEWDELHI
import com.mytutorial.model.LONDON

@RunWith(classOf[JUnitRunner])
class WeatherDataLoaderTest extends FunSuite with BeforeAndAfter {
 
  test("8 temp records are loaded") {
    assert(WeatherDataLoader.loadTempTrainingData.size == 8)
  }
  
  test("SYDNEY temp record is correct") {
    val temperatures = WeatherDataLoader.loadTempTrainingData;
    assert(temperatures(0).location == SYDNEY )
    assert(temperatures(0).springAvgTemp == 27.0 )
    assert(temperatures(0).summerAvgTemp == 34.0 )
    assert(temperatures(0).autumnAvgTemp == 23.0 )
    assert(temperatures(0).winterAvgTemp == 18.0 )
  }
  
   test("TOKYO temp record is correct") {
    val temperatures = WeatherDataLoader.loadTempTrainingData;
    assert(temperatures(7).location == TOKYO )
    assert(temperatures(7).springAvgTemp == 20.0 )
    assert(temperatures(7).summerAvgTemp == 25.0 )
    assert(temperatures(7).autumnAvgTemp == 18.0 )
    assert(temperatures(7).winterAvgTemp == 15.0 )
  }
   
   
  test("NEWDELHI temp record is correct") {
    val temperatures = WeatherDataLoader.loadTempTrainingData;
    assert(temperatures(6).location == NEWDELHI )
    assert(temperatures(6).springAvgTemp == 24.0 )
    assert(temperatures(6).summerAvgTemp == 31.0 )
    assert(temperatures(6).autumnAvgTemp == 19.0 )
    assert(temperatures(6).winterAvgTemp == 15.0 )
  } 
  
  
  test("8 dew point temp records are loaded") {
    assert(WeatherDataLoader.loadDewPointTempTrainingData.size == 8)
  }
  
  test("SYDNEY dew point temp record is correct") {
    val temperatures = WeatherDataLoader.loadDewPointTempTrainingData;
    assert(temperatures(0).location == SYDNEY )
    assert(temperatures(0).dpTempJan == 12.5)
    assert(temperatures(0).dpTempFeb == 11.6)
    assert(temperatures(0).dpTempMarch == 17.6)
    assert(temperatures(0).dpTempApril == 15.2)
  } 
  
}