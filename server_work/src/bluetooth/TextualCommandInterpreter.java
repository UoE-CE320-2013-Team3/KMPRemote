package bluetooth;

import inputControllers.KeyboardInputControl;
import inputControllers.MouseInputControl;

import java.awt.*;
import java.util.Stack;

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
    private static final String DOWN_DIRECTION_CMD = "DOWN_DIRECTION";
    private static final String UP_DIRECTION_CMD = "UP_DIRECTION";
    private static final String LEFT_DIRECTION_CMD = "LEFT_DIRECTION";
    private static final String RIGHT_DIRECTION_CMD = "RIGHT_DIRECTION";
    private static final String MOVE_CMD = "MOVE";
    private static final String TOGGLE_CMD = "TOGGLE";
    private static final String HOLD_CMD = "HOLD";
    private static final String RELEASE_CMD = "RELEASE";
    private static final String ALL_CMD = "ALL";

    private Stack<String> commandTokens;
    private MouseInputControl mouseInputControl;
    private KeyboardInputControl keyboardInputControl;

    //TODO keyboard instance variables.

    // Examples of Parsable messages.
    // KEYBOARD TOGGLE a
    // KEYBOARD HOLD a b c
    // KEYBOARD RELEASE a b c
    // MOUSE MOVE UP 20
    // MOUSE MOVE DOWN 50
    // MOUSE TOGGLE leftclick
    // MOUSE HOLD leftclick
    // MOUSE RELEASE leftclick
    // KEYBOARD RELEASE ALL
    // MOUSE RELEASE ALL

    public TextualCommandInterpreter(String commands) throws AWTException {
        commandTokens = new Stack<String>();
        for (String command : commands.split("\\s+")) {
            commandTokens.add(command);
        }
        mouseInputControl = new MouseInputControl();
        keyboardInputControl = new KeyboardInputControl();
    }

    public void processCommand() throws NoSuchCommandException {
        while (!commandTokens.empty()) {
            String commandWord = commandTokens.pop();
            if (commandWord.equals(MOUSE_CMD)) {
                processMouse();
            } else if (commandWord.equals(KEYBOARD_CMD)) {
                //TODO processKeyboard();
            } else {
                throw new NoSuchCommandException(commandWord, MOUSE_CMD, KEYBOARD_CMD);
            }
        }

    }

    private void processMouse() {
        String commandWord = commandTokens.pop();
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
        String commandWord = commandTokens.pop();
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
        String commandWord = commandTokens.pop();
        if (commandWord.equals("LEFT")) {
            mouseInputControl.leftMouseButtonToggle();
        } else if (commandWord.equals(("RIGHT"))) {
            mouseInputControl.rightMouseButtonToggle();
        }
    }

    private void processMouseRelease() {
        String commandWord = commandTokens.pop();
        if (commandWord.equals("LEFT")) {
            mouseInputControl.leftMouseButtonRelease();
        }
    }

    private void processMouseHold() {
        String commandWord = commandTokens.pop();
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
        String commandWord = commandTokens.pop();
        return Integer.parseInt(commandWord);
    }

    public class NoSuchCommandException extends Throwable {
        String errorMessage;

        public NoSuchCommandException(String commandWord, String... acceptedCommands) {
            StringBuilder acceptedCommandsGenerator = new StringBuilder();
            acceptedCommandsGenerator.append(acceptedCommands[0]);
            for (int i = 1; i < acceptedCommands.length; i++) {
                acceptedCommandsGenerator.append(acceptedCommands[i]);
            }
            errorMessage = "Found :" + commandWord + " where " + acceptedCommandsGenerator.toString() + " was expected.";
        }

        public String getMessage() {
            return errorMessage;
        }
    }


}
