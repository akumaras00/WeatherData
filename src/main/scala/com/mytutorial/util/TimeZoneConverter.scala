package com.mytutorial.util

import org.joda.time.DateTimeZone
import com.mytutorial.model.Location
import com.mytutorial.model._
import org.joda.time.DateTime

/**
 * converts a given date time to its local date time based on the time zone
 */
object TimeZoneConverter {
  
  def localTimeToTargetLocation(givenTime: DateTime, location: Location): DateTime =  {
    
   val timeZone = location match {
      case SYDNEY => DateTimeZone.forID("Australia/Sydney")
      case MELBOURNE => DateTimeZone.forID("Australia/Melbourne")
      case AUCKLAND => DateTimeZone.forID("Pacific/Auckland")
      case LONDON => DateTimeZone.forID("Europe/London")
      case NEWYORK => DateTimeZone.forID("America/New_York")
      case LOSANGELES => DateTimeZone.forID("America/Los_Angeles")
      case NEWDELHI => DateTimeZone.forID("Asia/Calcutta")
      case TOKYO => DateTimeZone.forID("Asia/Tokyo")
    }
    
    givenTime.toDateTime(timeZone) 
  }
}