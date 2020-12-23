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
    void dateCheckInvalidFormatTest(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(INVALID_FORMAT, Validator.dateCheck("2020/11/02", dates, 1));
    }

    @Test
    void dateCheckMontDaysSurpassedTest(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(MONTH_DAYS_SURPASSED, Validator.dateCheck("2020-02-30", dates, 1));
    }

    @Test
    void dateCheckAlreadyUsedTest(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(ALREADY_USED, Validator.dateCheck("2020-11-01", dates, 1));
    }

    @Test
    void dateCheckBeforePreviousDateTest(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(BEFORE_PREVIOUS_DATE, Validator.dateCheck("2020-10-01", dates, 1));
    }

    @Test
    void dateCheckValidTest(){
        dates.add(LocalDate.of(2020,11,1));
        assertEquals(VALID, Validator.dateCheck("2020-11-02", dates, 1));
    }

    @Test
    void isLeapYearPositiveTest(){
        assertTrue(Validator.isLeapYear(2020));
    }

    @Test
    void isLeapYearNegativeTest(){
        assertFalse(Validator.isLeapYear(2021));
    }

    @Test
    void intCheckValidRangeTest() {
        assertEquals(VALID, Validator.intCheck("2",0,3));
    }

    @Test
    void intCheckValidListTest() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        assertEquals(VALID, Validator.intCheck("2", list));
    }

    @Test
    void intCheckNotInTheListTest() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        assertEquals(NOT_IN_THE_LIST, Validator.intCheck("6", list));
    }

    @Test
    void intCheckInvalidFormatTest() {
        assertEquals(INVALID_FORMAT, Validator.intCheck("2sa",0,3));
    }

    @Test
    void intCheckOutOfRangeTest() {
        assertEquals(OUT_OF_RANGE, Validator.intCheck("5",0,3));
    }

    @Test
    void emptyStringCheckPositiveTest(){
        assertEquals(VALID, Validator.emptyStringCheck("Some string"));
    }

    @Test
    void emptyStringCheckNegativeTest(){
        assertEquals(EMPTY_STRING, Validator.emptyStringCheck(" "));
    }

}