package test.utils;

import main.utils.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.Globals.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void dateCheckPositive() {
        assertTrue(Validator.dateCheck("2020-10-10"));
    }

    @Test
    void dateCheckNegative1() {
        assertTrue(Validator.dateCheck("10-10-2020"));
    }

    @Test
    void dateCheckNegative2() {
        assertTrue(Validator.dateCheck("2020-13-10"));
    }

    @Test
    void dateCheckNegative3() {
        assertTrue(Validator.dateCheck("2020-12-40"));
    }

    @Test
    void dateCheckNegative4() {
        assertTrue(Validator.dateCheck("2020/10/10"));
    }

    @Test
    void dateCheckNegative5() {
        assertTrue(Validator.dateCheck("randomString"));
    }

    @Test
    void intCheckPositive1() {
        assertEquals(VALID, Validator.intCheck("2",0,3));
    }

    @Test
    void intCheckPositive2() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        assertEquals(VALID, Validator.intCheck("2", list));
    }

    @Test
    void intCheckPositive3() {
        assertEquals(INVALID_FORMAT, Validator.intCheck("2sa",0,3));
    }

    @Test
    void intCheckPositive4() {
        assertEquals(OUT_OF_RANGE, Validator.intCheck("5",0,3));
    }

}