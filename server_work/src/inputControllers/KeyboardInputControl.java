package inputControllers; /**
 * Created with IntelliJ IDEA.
 * User: john, mhpric
 * Date: 11/14/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */

/*
 *  Allowable commands:
 *      Characters (not to be used for keyHold or keyRelease): [a-Z] [1-9] !"�$%&*()-_=+[]{}'#~.,
 *      Strings:
 *          SHIFT
 *          CTRL
 *          ALT
 *          TAB
 *          SPACE
 *          CAPS
 *          ENTER
 *          BACKSPACE
 *			F1
 *			F2
 *			F3
 *			F4
 *          F5
 *          F6
 *			F7
 *			F8
 *			F9
 *			F10
 *			F11
 *			F12
 *          RIGHTARROW
 *          LEFTARROW
 *			UPARROW
 *			DOWNARROW
 *          PAGEUP
 *          PAGEDOWN
 *          ESC
 *
 *  To add new button functionality, add a new 'else if' statement to the
 *  returnKeyCodeForString function in a format copying the other statements
 *  For the correct KeyEvent button reference, refer to the KeyEvent API.
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
        else if (string.equals("F1")) {
            return KeyEvent.VK_F1;
        }
		else if (string.equals("F2")) {
            return KeyEvent.VK_F2;
        }
		else if (string.equals("F3")) {
            return KeyEvent.VK_F3;
        }
		else if (string.equals("F4")) {
            return KeyEvent.VK_F4;
        }
		else if (string.equals("F5")) {
            return KeyEvent.VK_F5;
        }
		else if (string.equals("F6")) {
            return KeyEvent.VK_F6;
        }
		else if (string.equals("F7")) {
            return KeyEvent.VK_F7;
        }
		else if (string.equals("F8")) {
            return KeyEvent.VK_F8;
        }
		else if (string.equals("F9")) {
            return KeyEvent.VK_F9;
        }
		else if (string.equals("F10")) {
            return KeyEvent.VK_F10;
        }
		else if (string.equals("F11")) {
            return KeyEvent.VK_F11;
        }
		else if (string.equals("F12")) {
            return KeyEvent.VK_F12;
        }
        else if (string.equals("RIGHTARROW")) {
            return KeyEvent.VK_RIGHT;
        }
        else if (string.equals("LEFTARROW")) {
            return KeyEvent.VK_LEFT;
        }
		else if (string.equals("UPARROW")) {
            return KeyEvent.VK_UP;
        }
        else if (string.equals("DOWNARROW")) {
            return KeyEvent.VK_DOWN;
        }
        else if (string.equals("PAGEUP")) {
            return KeyEvent.VK_PAGE_UP;
        }
        else if (string.equals("PAGEDOWN")) {
            return KeyEvent.VK_PAGE_DOWN;
        }
        else if (string.equals("ESC")) {
            return KeyEvent.VK_ESCAPE;
        }
		else if (string.equals(".")) {
            return KeyEvent.VK_PERIOD;
        }
		else if (string.equals(",")) {
            return KeyEvent.VK_COMMA;
        }
		else if (string.equals("/")) {
            return KeyEvent.VK_SLASH;
        }
		else if (string.equals("\\")) {
            return KeyEvent.VK_BACK_SLASH;
        }
		else if (string.equals(":")) {
            return KeyEvent.VK_COLON;
        }
		else if (string.equals(""")) {
            return KeyEvent.VK_QUOTEDBL;
        }
    	//**v
		else if (string.equals("<")) {
            return KeyEvent.VK_LESS;
        }
		//**v
		else if (string.equals(">")) {
            return KeyEvent.VK_COLON;
        }
		else if (string.equals("&")) {
            return KeyEvent.VK_AMPERSAND;
        }
		else if (string.equals("!")) {
            return KeyEvent.VK_EXCLAMATION_MARK;
        }
		else if (string.equals("#")) {
            return KeyEvent.VK_NUMBER_SIGN;
        }
		else if (string.equals("$")) {
            return KeyEvent.VK_DOLLAR;
        }
		//**v
		else if (string.equals("�")) {
            return KeyEvent.VK_COLON;
        }
		//**v
		else if (string.equals("?")) {
            return KeyEvent.VK_COLON;
        }
		else if (string.equals("=")) {
            return KeyEvent.VK_EQUALS;
        }
		//**v
		else if (string.equals("%")) {
            return KeyEvent.VK_COLON;
        }
		else if (string.equals("'")) {
            return KeyEvent.VK_QUOTE;
        }
		else if (string.equals("(")) {
            return KeyEvent.VK_LEFT_PARENTHESIS;
        }
		else if (string.equals(")")) {
            return KeyEvent.VK_RIGHT_PARENTHESIS;
        }
		//**v
		else if (string.equals("*")) {
            return KeyEvent.VK_COLON;
        }
		else if (string.equals("+")) {
            return KeyEvent.VK_PLUS;
        }
		else if (string.equals("-")) {
            return KeyEvent.VK_COLON;
        }
		else if (string.equals("[")) {
            return KeyEvent.VK_OPEN_BRACKET;
        }
		else if (string.equals("]")) {
            return KeyEvent.VK_CLOSE_BRACKET;
        }
		else if (string.equals("_")) {
            return KeyEvent.VK_UNDERSCORE;
        }
		//**v
		else if (string.equals("{")) {
            return KeyEvent.VK_COLON;
        }
		//**v
		else if (string.equals("}")) {
            return KeyEvent.VK_COLON;
        }
		//**v
		else if (string.equals("~")) {
            return KeyEvent.VK_COLON;
        }
		else if (string.equals("COPY")) {
            return KeyEvent.VK_COPY;
        }
        else {
            throw new NoSuchKeyException(string);
        }

    }

    public class NoSuchKeyException extends RuntimeException {
        private NoSuchKeyException(char character) {
            super(character + "is not a valid character. Character must " +
                  "be in range [a-Z] or [1-9]");
        }
        private NoSuchKeyException(String string) {
            super(string + "is not a valid string. Valid strings include:\n" +
                  "SHIFT\nCTRL\nALT\nTAB\nSPACE\nCAPS\nENTER\nBACKSPACE\n" +
                  "F5\nF6\nRIGHTARROW\nLEFTARROW\nPAGEUP\nPAGEDOWN\nESC\n");

        }
    }

}
