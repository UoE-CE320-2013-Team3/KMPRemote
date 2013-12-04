package bluetooth;

import inputControllers.KeyboardInputControl;
import inputControllers.MouseInputControl;
import inputControllers.PresentationInputControl;

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
    private static final String PRESENTATION_CMD = "PRESENTATION";
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
    private static final String RELEASE_ALL_CMD = "RELEASE_ALL";
    private static final String SCROLL_CMD = "SCROLL";
    private static final String LEFT_CLICK_CMD = "leftclick";
    private static final String RIGHT_CLICK_CMD = "rightclick";
    private static final String END_OF_LINK_CMD = "ENDOFLINK";

    private Queue<String> commandTokens;
    private MouseInputControl mouseInputControl;
    private KeyboardInputControl keyboardInputControl;

    private Stack<String> currentCommandTokensParsedLog;
    private String presentationLink;

    //TODO keyboard instance variables.
//TODO refactor order of methods.
    // Examples of Parsable messages.
  /*
  Allowable keyboard commands:
      Characters (not to be used for keyHold or keyRelease): [a-Z] [1-9]
      Strings:
          SHIFT
          CTRL
          ALT
          TAB
          SPACE
          CAPS
          ENTER
          BACKSPACE


    KEYBOARD TOGGLE a b c // Toggle presses and releases buttons, in this case a, then b, then c.
    KEYBOARD HOLD CTRL SHIFT // Hold holds a button down indefinitely.
    KEYBOARD RELEASE TAB // Release releases a button held down.
    KEYBOARD RELEASE_ALL // This releases all held down buttons.


    MOUSE MOVE UP 20 // Moves the cursor up 20 pixels
    MOUSE MOVE DOWN 50 //Moves the cursor down 50 pixels
    MOUSE MOVE LEFT 100 // Moves the cursor left 100 pixels
    MOUSE MOVE RIGHT 30 // Moves the cursor right 30 pixels
    MOUSE TOGGLE leftclick // Presses and releases left mouse button.
    MOUSE HOLD leftclick // Holds left mouse button indefinitely.
    MOUSE RELEASE leftclick // Releases left mouse button.
    MOUSE TOGGLE rightclick // Presses and releases right mouse button.
    MOUSE SCROLL UP 10 // Scrolls the mouse 10 notches upwards.
    MOUSE SCROLL DOWN 10 // Scrolls the mouse 10 notches downwards.

    */



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
        }
    }

    public TextualCommandInterpreter(String commands, MouseInputControl mouseInputControl, KeyboardInputControl keyboardInputControl) {
        initCommands(commands);
        this.mouseInputControl = mouseInputControl;
        this.keyboardInputControl = keyboardInputControl;
    }

    private String getNextCommand() {
        String commandWord = commandTokens.remove();
        currentCommandTokensParsedLog.push(commandWord);
        return commandWord;
    }

    public void processCommand() throws NoSuchCommandException, KeyboardInputControl.NoSuchKeyException {
        while (!commandTokens.isEmpty()) {
            currentCommandTokensParsedLog = new Stack<String>();
            String commandWord = getNextCommand();
            if (commandWord.equals(MOUSE_CMD)) {
                processMouse();
            } else if (commandWord.equals(KEYBOARD_CMD)) {
                processKeyboard();
            } else if (commandWord.equals(PRESENTATION_CMD)) {
                processPresentation();
            }else {
                throw new NoSuchCommandException(MOUSE_CMD, KEYBOARD_CMD);
            }
        }

    }

    private void processPresentation() {
        String link = getPresentationLink();

        PresentationInputControl presentationInputControl = new PresentationInputControl(link);

    }

    private void processKeyboard() {
        String commandWord = getNextCommand();
        if (commandWord.equals(TOGGLE_CMD)) {
            processKeyboardToggle();
        } else if (commandWord.equals(RELEASE_CMD)) {
            processKeyboardRelease();
        } else if (commandWord.equals(HOLD_CMD)) {
            processKeyboardHold();

        }
        if (commandWord.equals(RELEASE_ALL_CMD)) {
            keyboardInputControl.keyReleaseAll();
        } else {

            //TODO refactor error checking.
        }
    }


    private void processKeyboardRelease()  {
        while (!commandTokens.isEmpty()) {
            String key = processKeyboardKey();
            keyboardInputControl.keyRelease(key);
        }
    }

    private void processKeyboardHold()  {
        while (!commandTokens.isEmpty()) {
            String key = processKeyboardKey();
            keyboardInputControl.keyHold(key);
        }
    }

    private void processKeyboardToggle()   {
        while (!commandTokens.isEmpty()) {
            String key = processKeyboardKey();
            keyboardInputControl.keyToggle(key);
        }

    }

    private String processKeyboardKey() {
        return getNextCommand();
    }


    private void processMouse() {
        String commandWord = getNextCommand();
        if (commandWord.equals(MOVE_CMD)) {
            processMouseMove();
        } else if (commandWord.equals(SCROLL_CMD)) {
            processMouseScroll();
        }
        else if (commandWord.equals(TOGGLE_CMD)) {
            processMouseToggle();
        } else if (commandWord.equals(RELEASE_CMD)) {
            processMouseRelease();
        } else if (commandWord.equals(HOLD_CMD)) {
            processMouseHold();
        } else {
            //TODO refactor error checking.
        }

    }

    private void processMouseScroll() {
        String commandWord = getNextCommand();
         if (commandWord.equals(UP_DIRECTION_CMD)) {
            processMouseScrollUp();
        } else if (commandWord.equals(DOWN_DIRECTION_CMD)) {
            processMouseScrollDown();
        }  else {
            //TODO refactor error checking.
        }
    }

    private void processMouseScrollDown() {
        int notches = getMouseNotches();
        mouseInputControl.rollMouseWheelDown(notches);
    }

    private void processMouseScrollUp() {
        int notches = getMouseNotches();
        mouseInputControl.rollMouseWheelUp(notches);
    }

    private int getMouseNotches() {
        String commandWord = getNextCommand();
        return Integer.parseInt(commandWord);
        //TODO HANDLE INCORRECT VALUE.
    }

    private void processMouseMove() {
        String commandWord = getNextCommand();
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
        String commandWord = getNextCommand();
        if (commandWord.equals(LEFT_CLICK_CMD)) {
            mouseInputControl.leftMouseButtonToggle();
        } else if (commandWord.equals((RIGHT_CLICK_CMD))) {
            mouseInputControl.rightMouseButtonToggle();
        }
    }

    private void processMouseRelease() {
        String commandWord = getNextCommand();
        if (commandWord.equals(LEFT_CLICK_CMD)) {
            mouseInputControl.leftMouseButtonRelease();
        }
    }

    private void processMouseHold() {
        String commandWord = getNextCommand();
        if (commandWord.equals(LEFT_CLICK_CMD)) {
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
        String commandWord = getNextCommand();
        return Integer.parseInt(commandWord);
    }

    public String getPresentationLink() {
        String presentationLink = "";
        while (!commandTokens.peek().equals(END_OF_LINK_CMD)) {
           presentationLink += getNextCommand();
        }
        //TODO Parse link into stuff.
        return presentationLink;
    }


    public class NoSuchCommandException extends RuntimeException {
        String errorMessage;

        public NoSuchCommandException(String... acceptedCommands) {
            StringBuilder acceptedCommandsGenerator = new StringBuilder();
            StringBuilder commandThatFailedWhenParsedGenerator = new StringBuilder();
            Iterator<String> commandLogIt = currentCommandTokensParsedLog.iterator();
            String currCommand = "";

            if (commandLogIt.hasNext()){
                currCommand = commandLogIt.next();
                commandThatFailedWhenParsedGenerator.append(currCommand);
            }

            while (commandLogIt.hasNext()) {
                currCommand = commandLogIt.next();
                                                                                commandThatFailedWhenParsedGenerator.append(", ");
                commandThatFailedWhenParsedGenerator.append(currCommand);
            }

            for (int i = 1; i < acceptedCommands.length; i++) {
                acceptedCommandsGenerator.append(Arrays.toString(acceptedCommands));
            }
            errorMessage = "Last command: '" + commandThatFailedWhenParsedGenerator.toString() + "' Found " + currCommand + " where " +  acceptedCommandsGenerator.toString() +  " was expected.";
            System.out.println("\""+errorMessage+"\"");
        }

        public String getMessage() {
            return errorMessage;
        }
    }


}
