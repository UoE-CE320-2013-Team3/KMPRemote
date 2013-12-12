package nonautomatabletests.manual;

/**
 * Created with IntelliJ IDEA.
 * User: john, mhpric
 * Date: 14/11/13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */

/**
 * First Test:
 *      This test is designed to test the methods of the KeyboardControl class
 *      To use this test:
 *          open notepad and type in a word
 *          move the cursor, select the notepad and highlight the word that has been written
 *          (must be done within 2 seconds)
 *          The written word will get cut and then the following should be printed:
 *          (note these results assume the written word was alpha and Caps lock is off)
 *              alpha test
 *              TEST
 *              test	TEST
 *              TEST
 *
 *              1234
 *              !"£$
 *          Once printed, after 2 seconds the $ and £ sounds should be deleted
 *          Then computer should then cycle through 2 "alt-Tab" cycles
 *          The program should then print out "Caught invalid string" to the standard
 *          output stream
 *
 * Second Test:
 *      After adding functionality to KeyboardInputControl, the new inputs (F5, F6, RIGHTARROW,
 *      LEFTARROW, PAGEUP, PAGEDOWN, ESC) needed to be test this. To use this test:
 *      Open Microsoft Powerpoint and create 2 pages
 *      Start the test
 *      Select Microsoft Powerpoint
 *      The test should start the presentation and then cycle move through the slides in
 *      the order; forward, backward, forward, backward, forward. The presentation should
 *      then exit and then restart on the second slide rather than the first
 */

import main.java.inputControllers.KeyboardInputControl;

import java.awt.*;

public class ManualKeyboardControlTest {

    public static void main(String args[]) throws AWTException {
        KeyboardInputControl keyControl = new KeyboardInputControl();

        keyControl.wait(2000);

        try {
//
//            keyControl.keyHold("CTRL");
//            keyControl.keyToggle("x");
//            keyControl.keyRelease("CTRL");
//
//            keyControl.wait(2000);
//
//            keyControl.keyHold("CTRL");
//            keyControl.keyToggle("v");
//            keyControl.keyRelease("CTRL");
//
//            keyControl.keyToggle("SPACE");
//
//            keyControl.keyToggle("t");
//            keyControl.keyToggle("e");
//            keyControl.keyToggle("s");
//            keyControl.keyToggle("t");
//
//            keyControl.keyToggle("ENTER");
//            keyControl.keyHold("SHIFT");
//
//            keyControl.keyToggle("t");
//            keyControl.keyToggle("e");
//            keyControl.keyToggle("s");
//            keyControl.keyToggle("t");
//
//            keyControl.keyReleaseAll();
//            keyControl.keyToggle("ENTER");
//
//            keyControl.keyToggle("t");
//            keyControl.keyToggle("e");
//            keyControl.keyToggle("s");
//            keyControl.keyToggle("t");
//
//            keyControl.keyToggle("TAB");
//
//            keyControl.keyToggle("T");
//            keyControl.keyToggle("E");
//            keyControl.keyToggle("S");
//            keyControl.keyToggle("T");
//
//            keyControl.keyToggle("ENTER");
//
//            keyControl.keyToggle("CAPS");
//
//            keyControl.keyToggle("t");
//            keyControl.keyToggle("e");
//            keyControl.keyToggle("s");
//            keyControl.keyToggle("t");
//
//            keyControl.keyToggle("CAPS");
//
//            keyControl.keyToggle("ENTER");
//
//            keyControl.keyToggle("1");
//            keyControl.keyToggle("2");
//            keyControl.keyToggle("3");
//            keyControl.keyToggle("4");
//
//            keyControl.keyToggle("ENTER");
//            keyControl.keyHold("SHIFT");
//
//            keyControl.keyToggle("1");
//            keyControl.keyToggle("2");
//            keyControl.keyToggle("3");
//            keyControl.keyToggle("4");
//
//            keyControl.keyRelease("SHIFT");
//
//            keyControl.wait(2000);
//
//            keyControl.keyToggle("BACKSPACE");
//
//            keyControl.wait(2000);
//
//            keyControl.keyToggle("BACKSPACE");
//
//            keyControl.wait(2000);
//
//            keyControl.keyHold("ALT");
//            keyControl.keyToggle("TAB");
//
//            keyControl.wait(2000);
//
//            keyControl.keyToggle("TAB");
//
//            keyControl.wait(2000);
//
//            keyControl.keyRelease("ALT");

            //start of new tests

            keyControl.keyToggle("F5");

            keyControl.wait(1000);

            keyControl.keyToggle("RIGHTARROW");

            keyControl.wait(1000);

            keyControl.keyToggle("LEFTARROW");

            keyControl.wait(1000);

            keyControl.keyToggle("ENTER");

            keyControl.wait(1000);

            keyControl.keyToggle("PAGEUP");

            keyControl.wait(1000);

            keyControl.keyToggle("PAGEDOWN");

            keyControl.wait(1000);

            keyControl.keyToggle("ESC");

            keyControl.wait(1000);

            keyControl.keyHold("SHIFT");

            keyControl.keyToggle("F5");

            keyControl.keyRelease("SHIFT");

            keyControl.wait(1000);

            keyControl.keyToggle("ESC");

            keyControl.wait(1000);

            keyControl.keyToggle("F6");

            keyControl.wait(1000);

            keyControl.keyToggle("F6");

            keyControl.wait(1000);

            keyControl.keyToggle("F6");

            keyControl.wait(1000);

            keyControl.keyToggle("F6");



            try {
                keyControl.keyToggle("notAValidString");
            } catch (KeyboardInputControl.NoSuchKeyException e) {
                System.out.println("Caught invalid string");
            }

        } catch (KeyboardInputControl.NoSuchKeyException e) {
            e.printStackTrace();
        }


    }

}
