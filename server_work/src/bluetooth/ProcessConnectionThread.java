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
    private static final int END_CMD = -2;
    private static final int END_SESSION = -1;


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
            System.out.println("waiting for input");
            List<Byte> stream = new ArrayList<Byte>();
            while (true) {
                byte commandByte = (byte) inputStream.read();
                System.out.println("Received byte: "+commandByte);
                if (commandByte == (END_CMD)) {
                    try {
                        //TODO parse the command into a string, print out errors.
                        //TODO handle end of session

                        try {
                            byte[] commandBytes = new byte[stream.size()];
                            int i = 0;
                            for (Byte aByte : stream) {
                                commandBytes[i++] = aByte;
                            }

                            String parsedCommand = new String(commandBytes);
                            System.out.println("Command about to be parsed: "+parsedCommand);
                            new TextualCommandInterpreter(parsedCommand).processCommand();
                            stream = new ArrayList<Byte>();
                        } catch (KeyboardInputControl.NoSuchKeyException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    } catch (TextualCommandInterpreter.NoSuchCommandException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    System.out.println("End of command.");
                }
                else if (commandByte == END_SESSION) {
                    break;
                }
                else {
                    System.out.println("Adding command byte to stream: "+commandByte);
                    stream.add(commandByte);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Socket closing...");
    }


}

