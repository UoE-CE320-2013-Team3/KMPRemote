package bluetooth;

import inputControllers.KeyboardInputControl;
import inputControllers.MouseInputControl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 11/16/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextualCommandInterpreterTest {
      TextualCommandInterpreter textualCommandInterpreter;
      @Mock
      MouseInputControl mouseInputControlMock;
      @Mock
      KeyboardInputControl keyboardInputControlMock;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ArgumentCaptor<MouseInputControl> mouseInputControlArgumentCaptor = ArgumentCaptor.forClass(MouseInputControl.class);
        ArgumentCaptor<KeyboardInputControl> keyboardInputControlArgumentCaptor = ArgumentCaptor.forClass(KeyboardInputControl.class);
    }

    private TextualCommandInterpreter textualCommandInterpreterFactory(String command) {
        return new TextualCommandInterpreter(String command, mouseInputControlMock, keyboardInputControlMock);
    }



    @Test
    public void testProcessCommand() throws Exception {

    }

    @Test
    public void testMouseUp() throws Exception, TextualCommandInterpreter.NoSuchCommandException, KeyboardInputControl.NoSuchKeyException {
     textualCommandInterpreterFactory("MOUSE UP 20").processCommand();
     Mockito.verify(mouseInputControlMock).moveMouseUp(20);
    }



}
