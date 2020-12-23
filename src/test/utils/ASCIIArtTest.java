package test.utils;

import main.utils.ASCIIArt;
import org.junit.jupiter.api.Test;

class ASCIIArtTest {

    @Test
    void printLogoTest() {
        ASCIIArt.printLogo();
    }

    @Test
    void enterTest() {
        ASCIIArt.enter();
    }

    @Test
    void nextTest() {
        ASCIIArt.next();
    }

    @Test
    void failTest() {
        ASCIIArt.fail();
    }

    @Test
    void successTest() {
        ASCIIArt.success();
    }

    @Test
    void penaltiesTest() {
        ASCIIArt.penalties();
    }

    @Test
    void championTest() {
        ASCIIArt.champion();
    }

    @Test
    void endTest() {
        ASCIIArt.end();
    }

    @Test
    void completeTest() {
        ASCIIArt.complete();
    }
}