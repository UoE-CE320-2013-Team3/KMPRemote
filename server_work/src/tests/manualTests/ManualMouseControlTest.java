package tests.manualTests;

/**
 * Created with IntelliJ IDEA.
 * User: john, mhpric
 * Date: 16/11/13
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */

/**
 * Test Purpose:
 *      This test is designed to test all the methods of the inputControllers.MouseControl class.
 *      Step by step it will:
 *          Hold left mouse button
 *          Move cursor up, left, down and right (ending up back in start location)
 *          Release left mouse button
 *          Move cursor up
 *          Right click
 *          Move cursor down and then right
 *          Left click
 *
 */

import inputControllers.*;
import java.awt.*;

public class ManualMouseControlTest {


    public static void main(String args[]) throws AWTException {
        inputControllers.MouseInputControl mouseControl = new MouseInputControl();
        inputControllers.KeyboardInputControl keyControl = new KeyboardInputControl();

        keyControl.wait(2000);

        mouseControl.leftMouseButtonHold();

        for (int x = 0; x < 100; x++) {
        mouseControl.moveMouseUp(1);
        keyControl.wait(20);
        }

        for (int x = 0; x < 100; x++) {
            mouseControl.moveMouseLeft(1);
            keyControl.wait(20);
        }

        for (int x = 0; x < 100; x++) {
            mouseControl.moveMouseDown(1);
            keyControl.wait(20);
        }

        for (int x = 0; x < 100; x++) {
            mouseControl.moveMouseRight(1);
            keyControl.wait(20);
        }

        mouseControl.leftMouseButtonRelease();

        for (int x = 0; x < 100; x++) {
            mouseControl.moveMouseUp(1);
            keyControl.wait(20);
        }

        mouseControl.rightMouseButtonToggle();

        for (int x = 0; x < 85; x++) {
            mouseControl.moveMouseDown(1);
            keyControl.wait(20);
        }

        for (int x = 0; x < 40; x++) {
            mouseControl.moveMouseRight(1);
            keyControl.wait(20);
        }

        mouseControl.leftMouseButtonToggle();


    }

}
