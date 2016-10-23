package com.mytutorial.loader

import scala.io.Source
import com.mytutorial.model.AvgTemperatureLearningData
import com.mytutorial.model.SYDNEY
import com.mytutorial.model.Location
import com.mytutorial.model.AvgDewPointTemp


object WeatherDataLoader {
  
  /**
   * Loads weather temperatures training data from a csv file
   */
  def loadTempTrainingData: List[AvgTemperatureLearningData] = {
    val src = Source.fromInputStream(getClass.getResourceAsStream("/TempTrainingData.csv"))
    val iter = src.getLines().drop(1).map(_.split(","))
    iter.map(row => new AvgTemperatureLearningData(SYDNEY.getLocationByString(row(0)),
        row(1).toDouble, row(2).toDouble, row(3).toDouble, row(4).toDouble)
    ).toList
  }
  
  
   /**
   * Loads Dew Point temperatures training data from a csv file
   */
  def loadDewPointTempTrainingData: List[AvgDewPointTemp] = {
    val src = Source.fromInputStream(getClass.getResourceAsStream("/DewPointTempByMonth.csv"))
    val iter = src.getLines().drop(1).map(_.split(","))
    iter.map(row => new AvgDewPointTemp(SYDNEY.getLocationByString(row(0)),
        row(1).toDouble, row(2).toDouble, row(3).toDouble, row(4).toDouble,
         row(5).toDouble,  row(6).toDouble, row(7).toDouble, row(8).toDouble,
          row(9).toDouble,  row(10).toDouble, row(11).toDouble,  row(12).toDouble)
    ).toList
  }
  
}