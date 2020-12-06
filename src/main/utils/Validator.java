package main.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.resources.Globals.*;


public class Validator {

    public static String dateCheck(String input, List<LocalDate> dates, int index) {

        final String DATE_PATTERN = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(DATE_PATTERN);

        boolean validFormat = false;
        String message= "";
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches() && input.length() == 10) {

            // assign true first, later we will check the leap year and odd or even months
            validFormat = true;

            int year = Integer.parseInt(matcher.group(1));

            // why string? month matches 02 or 2
            String month = matcher.group(2);
            String day = matcher.group(3);

            // 30 or 31 days checking
            // only 1,3,5,7,8,10,12 has 31 days
            if ((month.equals("4") || month.equals("6") || month.equals("9") ||
                    month.equals("04") || month.equals("06") || month.equals("09") ||
                    month.equals("11")) && day.equals("31")) {
                message = MONTH_DAYS_SURPASSED;
                validFormat = false;
            } else if (month.equals("2") || month.equals("02")) {
                if (day.equals("30") || day.equals("31")) {
                    message = MONTH_DAYS_SURPASSED;
                    validFormat = false;
                } else if (day.equals("29")) {  // leap year? feb 29 days.
                    if (!isLeapYear(year)) {
                        message = LEAP_YEAR;
                        validFormat = false;
                    }
                }
            }
        }else{
            message = INVALID_FORMAT;
        }

        if(validFormat){
            LocalDate date = LocalDate.parse(input);
            if(dates.contains(date)){
                message = ALREADY_USED;
            }
            else{
                if(index != 0 && dates.get(index-1).isAfter(date)){
                    message = BEFORE_PREVIOUS_DATE;
                }
                else{
                    message = VALID;
                }
            }
        }
        return message;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

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
                return NOT_IN_THE_LIST;
            }
        } catch (Exception e) {
            return INVALID_FORMAT;
        }
    }

    public static String emptyStringCheck(String input) {
        if(input.trim().equals("")){
            return EMPTY_STRING;
        }
        else{
            return VALID;
        }
    }

}
