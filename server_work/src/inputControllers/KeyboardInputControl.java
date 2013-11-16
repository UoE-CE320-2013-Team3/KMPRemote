package inputControllers; /**
 * Created with IntelliJ IDEA.
 * User: john, mhpric
 * Date: 11/14/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */

/*
 *  Allowable commands:
 *      Characters (not to be used for keyHold or keyRelease): [a-Z] [1-9]
 *      Strings:
 *          SHIFT
 *          CTRL
 *          ALT
 *          TAB
 *          SPACE
 *          CAPS
 *          ENTER
 *          BACKSPACE
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class KeyboardInputControl {
    Robot robot;
    Stack<Character> heldKeys = new Stack<Character>();

    public KeyboardInputControl() throws AWTException {
        robot = new Robot();
    }

    public void keyToggle(String... stringArray) throws NoSuchKeyException {

        char character;
        char keyCode;

        for (String string : stringArray) {

           //if character
           if (string.length() == 1) {
               character = string.charAt(0);
               keyCode = returnKeyCodeForCharacter(character);

               if (Character.isUpperCase(character)) {
                   robot.keyPress(KeyEvent.VK_SHIFT);
               }

               robot.keyPress(keyCode);
               robot.keyRelease(keyCode);

               if (Character.isUpperCase(character)) {
                   robot.keyRelease(KeyEvent.VK_SHIFT);
               }

           }
           //if string
           else {
               keyCode = returnKeyCodeForString(string);
               robot.keyPress(keyCode);
               robot.keyRelease(keyCode);
           }
        }
    }


    public void keyHold(String... stringArray) throws NoSuchKeyException {

        char keyCode;

        for (String string: stringArray) {
            keyCode = returnKeyCodeForString(string);

            robot.keyPress(keyCode);
            heldKeys.push(keyCode);
        }
    }

    public void keyRelease(String... stringArray) throws NoSuchKeyException {


        for (String string : stringArray) {
            robot.keyRelease(returnKeyCodeForString(string));
        }

    }

    public void keyReleaseAll() {
        char keyToBeReleased;

        while (!heldKeys.isEmpty()) {
            keyToBeReleased = heldKeys.pop();
            robot.keyRelease(keyToBeReleased);

        }
    }

    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public class NoSuchKeyException extends Throwable {
        private NoSuchKeyException(char character) {
            super(character + "is not a valid character");
        }
        private NoSuchKeyException(String string) {
            super(string + "is not a valid string");
        }
    }

    //deals with character input. If not [a-Z] or [1-9] throws exception
    private char returnKeyCodeForCharacter(char character) throws NoSuchKeyException {
        if (Character.isLetter(character) | Character.isDigit(character)) {

            return Character.toUpperCase(character);

        }

        else {
            throw new NoSuchKeyException(character);
        }
    }

    //deals with string input. Throws exception if string not recognized
    private char returnKeyCodeForString(String string) throws NoSuchKeyException {
        if (string.equals("SHIFT")) {
            return KeyEvent.VK_SHIFT;
        }
        else if (string.equals("CTRL")) {
            return KeyEvent.VK_CONTROL;
        }
        else if (string.equals("ALT")) {
            return KeyEvent.VK_ALT;
        }
        else if (string.equals("TAB")) {
            return KeyEvent.VK_TAB;
        }
        else if (string.equals("SPACE")) {
            return KeyEvent.VK_SPACE;
        }
        else if (string.equals("CAPS")) {
            return KeyEvent.VK_CAPS_LOCK;
        }
        else if (string.equals("ENTER")) {
            return KeyEvent.VK_ENTER;
        }
        else if (string.equals("BACKSPACE")) {
            return KeyEvent.VK_BACK_SPACE;
        }
        else {
            throw new NoSuchKeyException(string);
        }

    }
}
