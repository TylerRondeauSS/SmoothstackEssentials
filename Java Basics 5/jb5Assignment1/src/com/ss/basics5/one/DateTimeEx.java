package com.ss.basics5.one;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

// @author Tyler Rondeau


public class DateTimeEx {
	public static void main(String[] args) {
		//Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds?
		//(Year,Month,Day,Hour,Minute,Second,Ms)
		LocalDateTime birthday = LocalDateTime.of(1996, 4, 29, 02, 50, 17, 12);
		System.out.println(birthday);
		
		//Given a random date, how would you find the date of the previous Thursday?
		LocalDateTime ThursBefore = birthday.with(TemporalAdjusters.previous(DayOfWeek.THURSDAY));
		System.out.println(ThursBefore);
		
		//What is the difference between a ZoneId and a ZoneOffset?
		//Zoneid and Zoneoffset both track an offset of time from UTC, but zoneID also uses zonerules to determine how the offset varies 
		LocalDateTime todaySeoul = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		System.out.println(todaySeoul);
		//Zoneoffset tracks only the absolute offset from UTC
		OffsetDateTime ThirteenOffset = todaySeoul.plusHours(5).atOffset(ZoneOffset.ofHours(5));
		System.out.println(ThirteenOffset);
		
		//How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?
		//Convert an instant into a zoned date time for new york, the Instant will be in UTC
		Instant ins = Instant.now();
		ZonedDateTime insZDT = ins.atZone(ZoneId.of("America/New_York"));
		//Second way to do the same
		ZonedDateTime insZDT2 = ZonedDateTime.ofInstant(ins, ZoneId.of("America/New_York"));
		System.out.println(ins);
		System.out.println(insZDT);
		//Convert back into an instant, losing the zone and therefore going back to UTC
		ins = insZDT.toInstant();
		System.out.println(ins);
		
		//Write an example that, for a given year, reports the length of each month within that year.
		Year testYear = Year.now();
		System.out.println("The length of every month in the year : " + testYear.getValue());
		for(Month month : Month.values()) {
			YearMonth ym = YearMonth.of(testYear.getValue(), month.getValue());
			System.out.println("The month of " + month + " is " + ym.lengthOfMonth() + " days long");
		}
		
		//Write an example that, for a given month of the current year, lists all of the Mondays in that month.
		LocalDate date = testYear.atMonth(4).atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		Month apr = Month.APRIL;
		while(date.getMonth() == apr) {
			System.out.println(date);
			date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		}
		
		//Write an example that tests whether a given date occurs on Friday the 13th.
		DateTimeEx testSpooky = new DateTimeEx();
		testSpooky.testFriday13(date);
		LocalDate date2 = Year.of(2020).atMonth(11).atDay(13);
		testSpooky.testFriday13(date2);
		
	}
	public void testFriday13(LocalDate date) {
		//If it's the 13th day of the month, on a friday (5th day of the week)
		if(date.getDayOfMonth() == 13 && date.getDayOfWeek().getValue() == 5) {
			System.out.println("It is the spoooooky day, Friday the 13th!");
		}
		else{
			System.out.println("It is not Friday the 13th");
		}
	}
}
