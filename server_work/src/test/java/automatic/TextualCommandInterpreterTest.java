package test.java.automatic;

import main.java.bluetooth.TextualCommandInterpreter;
import main.java.inputControllers.KeyboardInputControl;
import main.java.inputControllers.MouseInputControl;
import main.java.inputControllers.PresentationInputControl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.*;

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
    @Mock
    PresentationInputControl presentationInputControlMock;

    InOrder mouseInputControlMockMethodInvocationOrderChecker;
    InOrder keyboardInputControlMockMethodInvocationOrderChecker;
    InOrder presentationInputControlMockMethodInvocationOrderChecker;
    
    static {
        //Otherwise AWTException thrown on headless server.
        System.setProperty("java.awt.headless", "false");
    }

    @Rule
    public ExpectedException expectedExSpecifier;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArgumentCaptor<MouseInputControl> mouseInputControlArgumentCaptor = ArgumentCaptor.forClass(MouseInputControl.class);
        ArgumentCaptor<KeyboardInputControl> keyboardInputControlArgumentCaptor = ArgumentCaptor.forClass(KeyboardInputControl.class);
        mouseInputControlMockMethodInvocationOrderChecker = inOrder(mouseInputControlMock);
        keyboardInputControlMockMethodInvocationOrderChecker = inOrder(keyboardInputControlMock);
        presentationInputControlMockMethodInvocationOrderChecker = inOrder(presentationInputControlMock);
        expectedExSpecifier = ExpectedException.none();
    }

    private TextualCommandInterpreter textualCommandInterpreterFactory(String command) {
        return new TextualCommandInterpreter(command, mouseInputControlMock, keyboardInputControlMock, presentationInputControlMock);
    }

    @Test
    public void shouldBeAbleToCreateNewInstanceByJustPassingInCommandString() throws AWTException {
        new TextualCommandInterpreter("COMMAND");
    }

    //TODO: Fix exception handling in textualCommandInterpreter
    @Test
    public void testMouseUp() {
        textualCommandInterpreterFactory("MOUSE MOVE UP 30").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseUp(30);
    }

    @Test
    public void testMouseLeft() {
        textualCommandInterpreterFactory("MOUSE MOVE LEFT 50").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseLeft(50);
    }

    @Test
    public void testMouseRight() {
        textualCommandInterpreterFactory("MOUSE MOVE RIGHT 300").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseRight(300);
    }

    @Test
    public void testMouseDown() {
        textualCommandInterpreterFactory("MOUSE MOVE DOWN 9").processCommand();
        Mockito.verify(mouseInputControlMock).moveMouseDown(9);
    }

    @Test
    public void testMouseLeftClickToggle() {
        textualCommandInterpreterFactory("MOUSE TOGGLE leftclick").processCommand();
        Mockito.verify(mouseInputControlMock).leftMouseButtonToggle();
    }

    @Test
    public void testMouseRightClickToggle() {
        textualCommandInterpreterFactory("MOUSE TOGGLE rightclick").processCommand();
        Mockito.verify(mouseInputControlMock).rightMouseButtonToggle();
    }

    @Test
    public void testMouseLeftClickHold() {
        textualCommandInterpreterFactory("MOUSE HOLD leftclick").processCommand();
        Mockito.verify(mouseInputControlMock).leftMouseButtonHold();
    }

    @Test
    public void testMouseLeftClickRelease() {
        textualCommandInterpreterFactory("MOUSE RELEASE leftclick").processCommand();
        Mockito.verify(mouseInputControlMock).leftMouseButtonRelease();
    }

    @Test
    public void testKeyboardToggleSingleLetter() {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE c").processCommand();
        Mockito.verify(keyboardInputControlMock).keyToggle("c");
    }

    @Test
    public void shouldInterpretMouseScrollUpCommands() {
        textualCommandInterpreterFactory("MOUSE SCROLL UP 10").processCommand();
        mouseInputControlMockMethodInvocationOrderChecker.verify(mouseInputControlMock).rollMouseWheelUp(10);
    }

    @Test
    public void shouldInterpretMouseScrollDownCommands() {
        textualCommandInterpreterFactory("MOUSE SCROLL DOWN 10").processCommand();
        mouseInputControlMockMethodInvocationOrderChecker.verify(mouseInputControlMock).rollMouseWheelDown(10);
    }

    @Test
    public void testKeyboardToggleMultipleLetters() {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE c a t").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("c");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("a");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("t");
    }

    @Test
    public void testKeyboardToggleSingleNumber() {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE 7").processCommand();
        Mockito.verify(keyboardInputControlMock).keyToggle("7");
    }

    @Test
    public void testKeyboardToggleMultipleNumbers() {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE 7 8 9").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("7");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("8");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("9");
    }

    @Test
    public void testKeyboardToggleSingleString() {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE BACKSPACE").processCommand();
        Mockito.verify(keyboardInputControlMock).keyToggle("BACKSPACE");
    }

    @Test
    public void testKeyboardToggleMultipleStrings() {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE BACKSPACE ENTER TAB").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("BACKSPACE");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("ENTER");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("TAB");
    }

    @Test
    public void testKeyboardToggleMultipleStringsNumbersLetters() {
        textualCommandInterpreterFactory("KEYBOARD TOGGLE c ENTER 7").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("c");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("ENTER");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyToggle("7");
    }

    @Test
    public void testKeyboardHoldOneKey() {
        textualCommandInterpreterFactory("KEYBOARD HOLD SHIFT").processCommand();
        Mockito.verify(keyboardInputControlMock).keyHold("SHIFT");
    }

    @Test
    public void testKeyboardHoldMultipleKeys() {
        textualCommandInterpreterFactory("KEYBOARD HOLD CTRL ALT").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyHold("CTRL");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyHold("ALT");
    }

    @Test
    public void testKeyboardReleaseOneKey() {
        textualCommandInterpreterFactory("KEYBOARD RELEASE CTRL").processCommand();
        Mockito.verify(keyboardInputControlMock).keyRelease("CTRL");
    }

    @Test
    public void testKeyboardReleaseMultipleKeys() {
        textualCommandInterpreterFactory("KEYBOARD RELEASE CTRL ALT SHIFT").processCommand();
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyRelease("CTRL");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyRelease("ALT");
        keyboardInputControlMockMethodInvocationOrderChecker.verify(keyboardInputControlMock).keyRelease("SHIFT");
    }

    @Test
    public void testKeyboardReleaseAll() {
        textualCommandInterpreterFactory("KEYBOARD RELEASE_ALL").processCommand();
        Mockito.verify(keyboardInputControlMock).keyReleaseAll();
    }

    /*
    @Test
    public void shouldCreatePresentationObjectWhenGivenLink() {
        PresentationInputControl presentationInputCont = mock(PresentationInputControl.class);
        when(presentationInputCont.getNotesAsJSON()).thenReturn("SUCCESS");
        try {
            PowerMockito.whenNew(PresentationInputControl.class).withArguments("M:\\_Year 3\\CE320\\PowerpointAPITest\\TesterPowerpoint.ppt").thenReturn(presentationInputCont);
        } catch (Exception e) {
            System.out.println("BLAH BLAH");
            e.printStackTrace();

        }
        TextualCommandInterpreter textualCommandInterpreter = textualCommandInterpreterFactory("PRESENTATION LINK M:\\_Year 3\\CE320\\PowerpointAPITest\\TesterPowerpoint.ppt END_LINK");
        textualCommandInterpreter.processCommand();
        textualCommandInterpreter.setCommands("PRESENTATION NOTES");
        assertEquals("SUCCESS", textualCommandInterpreter.processCommand());

        //textualCommandInterpreterFactory("PRESENTATION LINK M:\\_Year 3\\CE320\\PowerpointAPITest\\TesterPowerpoint.ppt END_LINK").processCommand();

    }
    */

    // "M:\\_Year 3\\CE320\\PowerpointAPITest\\TesterPowerpoint.ppt"
    @Test
    public void shouldReceiveNotesFromPresentation() {
        //textualCommandInterpreterFactory("PRESENTATION LINK M:\\_Year 3\\CE320\\PowerpointAPITest\\TesterPowerpoint.ppt END_LINK").processCommand();
        textualCommandInterpreterFactory("PRESENTATION NOTES").processCommand();
        presentationInputControlMockMethodInvocationOrderChecker.verify(presentationInputControlMock).getNotesAsJSON();
    }

}
