package com.mytutorial.util

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants.OCTOBER
import org.joda.time.DateTimeZone
import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import com.mytutorial.model.LONDON
import com.mytutorial.model.NEWYORK

@RunWith(classOf[JUnitRunner])
class TimeZoneConverterTest extends FunSuite with BeforeAndAfter {
  
   test("converted date time London test") {
      val givenLocalDateTime = new DateTime(2016,OCTOBER, 15, 17, 11, 0 )
      val convDateTime = TimeZoneConverter.localTimeToTargetLocation(givenLocalDateTime, LONDON) 
      val expectedDateTime = new DateTime(2016,OCTOBER, 15, 17, 11, 0 ).toDateTime(DateTimeZone.forID("Europe/London"))
      assert(convDateTime == expectedDateTime)
   }
   
   test("converted date time NEW YORK test") {
      val givenLocalDateTime = new DateTime(2016,OCTOBER, 15, 17, 11, 0 )
      val convDateTime = TimeZoneConverter.localTimeToTargetLocation(givenLocalDateTime, NEWYORK) 
      val expectedDateTime = new DateTime(2016,OCTOBER, 15, 17, 11, 0 ).toDateTime(DateTimeZone.forID("America/New_York"))
      assert(convDateTime == expectedDateTime)
   }
}