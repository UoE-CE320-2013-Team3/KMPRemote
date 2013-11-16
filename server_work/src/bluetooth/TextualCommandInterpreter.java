package bluetooth;

import inputControllers.KeyboardInputControl;
import inputControllers.MouseInputControl;

import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 11/16/13
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextualCommandInterpreter {

    // Constant that indicate command from devices
    private static final String MOUSE_CMD = "MOUSE";
    private static final String KEYBOARD_CMD = "KEYBOARD";
    private static final String UP_CMD = "UP";
    private static final String DOWN_CMD = "DOWN";
    private static final String DOWN_DIRECTION_CMD = "DOWN";
    private static final String UP_DIRECTION_CMD = "UP";
    private static final String LEFT_DIRECTION_CMD = "LEFT";
    private static final String RIGHT_DIRECTION_CMD = "RIGHT";
    private static final String MOVE_CMD = "MOVE";
    private static final String TOGGLE_CMD = "TOGGLE";
    private static final String HOLD_CMD = "HOLD";
    private static final String RELEASE_CMD = "RELEASE";
    private static final String ALL_CMD = "ALL";

    private Queue<String> commandTokens;
    private MouseInputControl mouseInputControl;
    private KeyboardInputControl keyboardInputControl;

    //TODO keyboard instance variables.

    // Examples of Parsable messages.
    // KEYBOARD TOGGLE a b c
    // KEYBOARD HOLD CTRL SHIFT
    // KEYBOARD RELEASE TAB
    // MOUSE MOVE UP 20
    // MOUSE MOVE DOWN 50
    // MOUSE MOVE LEFT 100
    // MOUSE MOVE RIGHT 30
    // MOUSE TOGGLE leftclick
    // MOUSE HOLD leftclick
    // MOUSE RELEASE leftclick
    // MOUSE TOGGLE rightclick
    // KEYBOARD RELEASE ALL


    //TODO ctrl tab tab, multiple key functionality, release all mouse, enter, return, backspace
    public TextualCommandInterpreter(String commands) throws AWTException {
        initCommands(commands);
        mouseInputControl = new MouseInputControl();
        keyboardInputControl = new KeyboardInputControl();
    }
    private void initCommands(String commands) {
    commandTokens = new LinkedList<String>();
    for (String command : commands.split("\\s+")) {
        commandTokens.add(command);
    }}

    public TextualCommandInterpreter(String commands, MouseInputControl mouseInputControl, KeyboardInputControl keyboardInputControl) {
        initCommands(commands);
        this.mouseInputControl = mouseInputControl;
        this.keyboardInputControl = keyboardInputControl;
    }
    public void processCommand() throws NoSuchCommandException, KeyboardInputControl.NoSuchKeyException {
        while (!commandTokens.isEmpty()) {
            String commandWord = commandTokens.remove();
            if (commandWord.equals(MOUSE_CMD)) {
                processMouse();
            } else if (commandWord.equals(KEYBOARD_CMD)) {
                processKeyboard();
            } else {
                throw new NoSuchCommandException(commandWord, MOUSE_CMD, KEYBOARD_CMD);
            }
        }

    }

    private void processKeyboard() throws KeyboardInputControl.NoSuchKeyException {
        String commandWord = commandTokens.remove();
        if (commandWord.equals(TOGGLE_CMD)) {
            processKeyboardToggle();
        } else if (commandWord.equals(RELEASE_CMD)) {
            processKeyboardRelease();
        } else if (commandWord.equals(HOLD_CMD)) {
            processKeyboardHold();
        } else {
            //TODO refactor error checking.
        }
    }

    private void processKeyboardRelease() throws KeyboardInputControl.NoSuchKeyException {
        String key = processKeyboardKey(); 
        keyboardInputControl.keyRelease(key);
    }
    
    private void processKeyboardHold() throws KeyboardInputControl.NoSuchKeyException {
        String key = processKeyboardKey();
        keyboardInputControl.keyHold(key);
    }
    
    private void processKeyboardToggle() throws KeyboardInputControl.NoSuchKeyException {
        String key = processKeyboardKey();
        keyboardInputControl.keyToggle(key);
    }

    private String processKeyboardKey() {
        //TODO Add validity checking
        return commandTokens.remove();
    }

    private void processMouse() {
        String commandWord = commandTokens.remove();
        if (commandWord.equals(MOVE_CMD)) {
            processMouseMove();
        } else if (commandWord.equals(TOGGLE_CMD)) {
            processMouseToggle();
        } else if (commandWord.equals(RELEASE_CMD)) {
            processMouseRelease();
        } else if (commandWord.equals(HOLD_CMD)) {
            processMouseHold();
        } else {
            //TODO refactor error checking.
        }

    }

    private void processMouseMove() {
        String commandWord = commandTokens.remove();
        if (commandWord.equals(UP_DIRECTION_CMD)) {
            processMouseMoveUp();
        } else if (commandWord.equals(DOWN_DIRECTION_CMD)) {
            processMouseMoveDown();
        } else if (commandWord.equals(RIGHT_DIRECTION_CMD)) {
            processMouseMoveRight();
        } else if (commandWord.equals(LEFT_DIRECTION_CMD)) {
            processMouseMoveLeft();
        }
    }


    private void processMouseToggle() {
        String commandWord = commandTokens.remove();
        if (commandWord.equals("LEFT")) {
            mouseInputControl.leftMouseButtonToggle();
        } else if (commandWord.equals(("RIGHT"))) {
            mouseInputControl.rightMouseButtonToggle();
        }
    }

    private void processMouseRelease() {
        String commandWord = commandTokens.remove();
        if (commandWord.equals("LEFT")) {
            mouseInputControl.leftMouseButtonRelease();
        }
    }

    private void processMouseHold() {
        String commandWord = commandTokens.remove();
        if (commandWord.equals("LEFT")) {
            mouseInputControl.leftMouseButtonHold();
        }
    }

    private void processMouseMoveUp() {
        int magnitude = processMagnitude();
        mouseInputControl.moveMouseUp(magnitude);
    }

    private void processMouseMoveDown() {
        int magnitude = processMagnitude();
        mouseInputControl.moveMouseDown(magnitude);
    }

    private void processMouseMoveRight() {
        int magnitude = processMagnitude();
        mouseInputControl.moveMouseRight(magnitude);
    }

    private void processMouseMoveLeft() {
        int magnitude = processMagnitude();
        mouseInputControl.moveMouseLeft(magnitude);
    }

    private int processMagnitude() {
        String commandWord = commandTokens.remove();
        return Integer.parseInt(commandWord);
    }
    

    public class NoSuchCommandException extends Exception {
        String errorMessage;

        public NoSuchCommandException(String commandWord, String... acceptedCommands) {
            StringBuilder acceptedCommandsGenerator = new StringBuilder();
            acceptedCommandsGenerator.append(acceptedCommands[0]);
            for (int i = 1; i < acceptedCommands.length; i++) {
                acceptedCommandsGenerator.append(", "+acceptedCommands[i]);
            }
            errorMessage = "Found :" + commandWord + " where " + acceptedCommandsGenerator.toString() + " was expected.";
        }

        public String getMessage() {
            return errorMessage;
        }
    }


}
