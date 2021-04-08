package com.ulc.tbr.models.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.temporal.*;
import java.time.*;
import java.util.Arrays;

public class CalendarTBR {
    public int year;
    public static String[][] dates;
    public static int[][] availability;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CalendarTBR(int year) {
        this.year = year;
        this.dates = new String[52][7];
        this.availability = new int[52][7];

        this.initializeCalendarTBR(year);

        //this.printDates(this.dates);
        //this.printAvailability(this.availability);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initializeCalendarTBR(int year) {

        // String [][] year = new String[52][7];

        YearMonth ym = YearMonth.of( year , Month.JANUARY ) ;
        LocalDate firstOfMonth = ym.atDay( 1 ) ;
        TemporalAdjuster ta = TemporalAdjusters.previousOrSame( DayOfWeek.SUNDAY ) ;
        LocalDate previousOrSameSunday = firstOfMonth.with( ta ) ;

        LocalDate endOfMonth = ym.atEndOfMonth();
        LocalDate weekStart = previousOrSameSunday ;

        for (int i = 0; i < this.dates.length; i++) {
            LocalDate currWeek = weekStart.plusWeeks( i );
            for (int j = 0; j < this.dates[0].length; j++) {
                LocalDate currDay = currWeek.plusDays( j ) ;
                this.dates[i][j] = currDay.toString();
                this.availability[i][j] = 0;
                // System.out.println("Week " + i + " Day " + j + " Date: " + currDay.toString());
            }
        }
    }

    public void printDates(String mat[][]) {
        // Loop through all rows
        for (String[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    public void printAvailability(int mat[][]) {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

}
