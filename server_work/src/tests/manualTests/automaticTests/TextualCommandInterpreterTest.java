package tests.automatic;

import bluetooth.TextualCommandInterpreter;
import inputControllers.KeyboardInputControl;
import inputControllers.MouseInputControl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.mockito.Mockito.inOrder;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 11/16/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextualCommandInterpreterTest {
    @Mock
    MouseInputControl mouseInputControlMock;
    @Mock
    KeyboardInputControl keyboardInputControlMock;
    InOrder mouseInputControlMockMethodInvocationOrderChecker;
    InOrder keyboardInputControlMockMethodInvocationOrderChecker;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArgumentCaptor<MouseInputControl> mouseInputControlArgumentCaptor = ArgumentCaptor.forClass(MouseInputControl.class);
        ArgumentCaptor<KeyboardInputControl> keyboardInputControlArgumentCaptor = ArgumentCaptor.forClass(KeyboardInputControl.class);
        mouseInputControlMockMethodInvocationOrderChecker = inOrder(mouseInputControlMock);
        keyboardInputControlMockMethodInvocationOrderChecker = inOrder(keyboardInputControlMock);
    }

    private TextualCommandInterpreter textualCommandInterpreterFactory(String command) {
        return new TextualCommandInterpreter(command, mouseInputControlMock, keyboardInputControlMock);
    }


    @Test
    public void testProcessCommand() throws Exception {

    }


    //TODO: Fix exception handling in textualCommandInterpreter
    @Test
    public void testMouseUp() throws Exception {
        textualCommandInterpreterFactory("MOUSE MOVE UP 30").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseUp(30);
    }

    @Test
    public void testMouseLeft() throws Exception {
        textualCommandInterpreterFactory("MOUSE MOVE LEFT 50").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseLeft(50);
    }

    @Test
    public void testMouseRight() throws Exception {
        textualCommandInterpreterFactory("MOUSE MOVE RIGHT 300").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseRight(300);
    }

    @Test
    public void testMouseDown() throws Exception {
        textualCommandInterpreterFactory("MOUSE MOVE DOWN 9").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseDown(9);
    }

    @Test
    public void testMouseLeftClickToggle() throws Exception {
        textualCommandInterpreterFactory("MOUSE TOGGLE leftclick").processCommand();
        Mockito.verify(mouseInputControlMock).leftMouseButtonToggle();
    }

    @Test
    public void testMouseRightClickToggle() throws Exception {
        textualCommandInterpreterFactory("MOUSE TOGGLE rightclick").processCommand();
        Mockito.verify(mouseInputControlMock).rightMouseButtonToggle();
    }

    @Test
    public void testMouseLeftClickHold() throws Exception {
        textualCommandInterpreterFactory("MOUSE HOLD leftclick").processCommand();
        Mockito.verify(mouseInputControlMock).leftMouseButtonHold();
    }

    @Test
    public void testMouseLeftClickRelease() throws Exception {
        textualCommandInterpreterFactory("MOUSE RELEASE leftclick").processCommand();
        Mockito.verify(mouseInputControlMock).leftMouseButtonRelease();
    }

    @Test
    public void testKeyboardToggleSingleLetter() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE c").processCommand();
        Mockito.verify(keyboardInputControlMock).keyToggle("c");
    }

    @Test
    public void testKeyboardToggleMultipleLetters() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE c a t").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("c");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("a");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("t");
    }

    @Test
    public void testKeyboardToggleSingleNumber() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE 7").processCommand();
        Mockito.verify(keyboardInputControlMock).keyToggle("7");
    }

    @Test
    public void testKeyboardToggleMultipleNumbers() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE 7 8 9").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("7");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("8");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("9");
    }

    @Test
    public void testKeyboardToggleSingleString() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE BACKSPACE").processCommand();
        Mockito.verify(keyboardInputControlMock).keyToggle("BACKSPACE");
    }

    @Test
    public void testKeyboardToggleMultipleStrings() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE BACKSPACE ENTER TAB").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("BACKSPACE");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("ENTER");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("TAB");
    }

    @Test
    public void testKeyboardToggleMultipleStringsNumbersLetters() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE c ENTER 7").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("c");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("ENTER");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("7");
    }

    @Test
    public void testKeyboardHoldOneKey() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD HOLD SHIFT").processCommand();
        Mockito.verify(keyboardInputControlMock).keyHold("SHIFT");
    }

    @Test
    public void testKeyboardHoldMultipleKeys() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD HOLD CTRL ALT").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyHold("CTRL");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyHold("ALT");
    }

    @Test
    public void testKeyboardReleaseOneKey() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD RELEASE CTRL").processCommand();
        Mockito.verify(keyboardInputControlMock).keyRelease("CTRL");
    }

    @Test
    public void testKeyboardReleaseMultipleKeys() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD RELEASE CTRL ALT SHIFT").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyRelease("CTRL");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyRelease("ALT");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyRelease("SHIFT");
    }

    @Test
    public void testKeyboardReleaseAll() throws Exception {
        textualCommandInterpreterFactory("KEYBOARD RELEASE_ALL").processCommand();
        Mockito.verify(keyboardInputControlMock).keyReleaseAll();
    }
}
