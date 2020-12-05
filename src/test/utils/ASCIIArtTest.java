package test.utils;

import main.utils.ASCIIArt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ASCIIArtTest {

    @Test
    void printLogo() {
        ASCIIArt.printLogo();
    }

    @Test
    void enter() {
        ASCIIArt.enter();
    }

    @Test
    void next() {
        ASCIIArt.next();
    }

    @Test
    void fail() {
        ASCIIArt.fail();
    }

    @Test
    void success() {
        ASCIIArt.success();
    }

    @Test
    void end() {
        ASCIIArt.end();
    }

    @Test
    void complete() {
        ASCIIArt.complete();
    }
}