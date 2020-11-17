package main.utils;

import main.Globals;
import main.KickOff;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.Globals.*;


public class Validator {

    public static boolean dateCheck(String input) {

        final String DATE_PATTERN = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(DATE_PATTERN);

        boolean result = false;
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {

            // assign true first, later we will check the leap year and odd or even months
            result = true;

            int year = Integer.parseInt(matcher.group(1));

            // why string? month matches 02 or 2
            String month = matcher.group(2);
            String day = matcher.group(3);

            // 30 or 31 days checking
            // only 1,3,5,7,8,10,12 has 31 days
            if ((month.equals("4") || month.equals("6") || month.equals("9") ||
                    month.equals("04") || month.equals("06") || month.equals("09") ||
                    month.equals("11")) && day.equals("31")) {
                result = false;
            } else if (month.equals("2") || month.equals("02")) {
                if (day.equals("30") || day.equals("31")) {
                    result = false;
                } else if (day.equals("29")) {  // leap year? feb 29 days.
                    if (!isLeapYear(year)) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

//    static boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
//        return !(testDate.before(startDate) || testDate.after(endDate));
//    }

    public static String intCheck(String input, int startOfRange, int endOfRange) {
        int integer;
        try {
            integer = Integer.parseInt(input);
            if(startOfRange <= integer && integer <= endOfRange){
                  return VALID;
            }
            else{
                return OUT_OF_RANGE;
            }
        } catch (Exception e) {
            return INVALID_FORMAT;
        }
    }

    public static String intCheck(String input, List<Integer> list) {
        int integer;
        try {
            integer = Integer.parseInt(input);
            if(list.contains(integer)){
                  return VALID;
            }
            else{
                return OUT_OF_RANGE;
            }
        } catch (Exception e) {
            return INVALID_FORMAT;
        }
    }

}
