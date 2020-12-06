package test.utils;

import main.utils.Validator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.resources.Globals.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    List<LocalDate> dates = new ArrayList<>();

    @Test
    void dateCheckInvalidFormat(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(INVALID_FORMAT, Validator.dateCheck("2020/11/02", dates, 1));
    }

    @Test
    void dateCheckMontDaysSurpassed(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(MONTH_DAYS_SURPASSED, Validator.dateCheck("2020-02-30", dates, 1));
    }

    @Test
    void dateCheckAlreadyUsed(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(ALREADY_USED, Validator.dateCheck("2020-11-01", dates, 1));
    }

    @Test
    void dateCheckBeforePreviousDate(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(BEFORE_PREVIOUS_DATE, Validator.dateCheck("2020-10-01", dates, 1));
    }

    @Test
    void dateCheckValid(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(VALID, Validator.dateCheck("2020-11-02", dates, 1));
    }

    @Test
    void isLeapYearPositive(){
        assertTrue(Validator.isLeapYear(2020));
    }

    @Test
    void isLeapYearNegative(){
        assertFalse(Validator.isLeapYear(2021));
    }

    @Test
    void intCheckValidRange() {
        assertEquals(VALID, Validator.intCheck("2",0,3));
    }

    @Test
    void intCheckValidList() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        assertEquals(VALID, Validator.intCheck("2", list));
    }

    @Test
    void intCheckNotInTheList() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        assertEquals(NOT_IN_THE_LIST, Validator.intCheck("6", list));
    }

    @Test
    void intCheckInvalidFormat() {
        assertEquals(INVALID_FORMAT, Validator.intCheck("2sa",0,3));
    }

    @Test
    void intCheckOutOfRange() {
        assertEquals(OUT_OF_RANGE, Validator.intCheck("5",0,3));
    }

    @Test
    void emptyStringCheckPositive(){
        assertEquals(VALID, Validator.emptyStringCheck("Some string"));
    }

    @Test
    void emptyStringCheckNegative(){
        assertEquals(EMPTY_STRING, Validator.emptyStringCheck(" "));
    }

}