package com.mytutorial.model

/**
 * Captures the geographic Location details
 */
sealed abstract class Location(val name: String, 
     val latitude: Double, val longitude: Double, 
    val elevation: Double) {
  
  
   def getLocationByString(location: String) : Location = {
     location.toUpperCase().trim() match {
       case "SYDNEY" => SYDNEY
       case "MELBOURNE" => MELBOURNE
       case "AUCKLAND" => AUCKLAND
       case "LONDON" => LONDON
       case "NEWYORK" => NEWYORK
       case "LOSANGELES" => LOSANGELES
       case "NEWDELHI" => NEWDELHI
       case "TOKYO" => TOKYO
     }
   }
   
   def getAllLocations: List[Location] = List(SYDNEY, MELBOURNE, AUCKLAND, LONDON, NEWYORK, LOSANGELES, NEWDELHI, TOKYO)
   
}

case object SYDNEY extends Location("SYDNEY", -33.86, 151.21, 19)
case object MELBOURNE extends Location("MELBOURNE", -37.81, 144.96, 7)
case object AUCKLAND extends Location("AUCKLAND", -36.8, 174.96, 79)
case object LONDON extends Location("LONDON", 51.5, 0.12, 35)
case object NEWYORK extends Location("NEWYORK", 40.7,	-74,	10)
case object LOSANGELES extends Location("LOSANGELES", 34.05,	-118.2,	71)
case object NEWDELHI extends Location("NEWDELHI", 28.65,	77.231,	213)
case object TOKYO extends Location("TOKYO", 35.68,	139.69,	56)