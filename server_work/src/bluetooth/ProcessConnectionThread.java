package bluetooth;

import inputControllers.KeyboardInputControl;

import javax.microedition.io.StreamConnection;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: //
 * Time: 2: PM
 * To change this template use File | Settings | File Templates.
 */
// Built on demonstration code found here: http://luugiathuy.com/2011/02/android-java-bluetooth/comment-page-2/

public class ProcessConnectionThread implements Runnable {
    private StreamConnection mConnection;

    private static final String LOGOUT_CMD = "LOGOUT";
    private static final int END_CMD = -1;


    //Keyboard constants
    private static final int KEY_RIGHT = 1;
    private static final int KEY_LEFT = 2;
    List<String> commands;
    Iterator<String> commandIt;


    // Examples of Parsable messages.
    // END
    // EXIT
    public ProcessConnectionThread(StreamConnection connection) {
        mConnection = connection;
        commands = new ArrayList<String>();
    }

    @Override
    public void run() {
        try {
            // prepare to receive data
            InputStream inputStream = mConnection.openInputStream();
            Scanner scan = new Scanner(inputStream);
            System.out.println("waiting for input");
            while (true) {
                String commandWord = scan.next();
                List<Byte> stream = new ArrayList<Byte>();
                byte commandByte = (byte) inputStream.read();
                if (commandByte == (END_CMD)) {
                    try {
                        //TODO parse the command into a string, print out errors.

                        try {
                            new TextualCommandInterpreter(stream.toString()).processCommand();
                        } catch (KeyboardInputControl.NoSuchKeyException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    } catch (TextualCommandInterpreter.NoSuchCommandException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    System.out.println("End of command.");
                }
                if (commandWord == (LOGOUT_CMD)) {
                    System.out.println("Terminating connection to client.");

                }
                else {
                    stream.add(commandByte);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

