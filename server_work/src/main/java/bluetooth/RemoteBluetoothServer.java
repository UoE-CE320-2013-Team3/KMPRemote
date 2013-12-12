package main.java.bluetooth;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 11/14/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
// Built on demonstration code found here: http://luugiathuy.com/2011/02/android-java-bluetooth/comment-page-2/
public class RemoteBluetoothServer {
    public static void main(String[] args) {
        // Frame code based on example in:  http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
        JFrame frame = new JFrame("Bluetooth Remote Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create components and put them in the frame.
        JLabel instructions = new JLabel("Close this window when you no longer want to be able to remotely control your device.");
        frame.getContentPane().add(instructions, BorderLayout.CENTER);

        //Size the frame.
        frame.pack();

        // Show it.
        frame.setVisible(true);
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();

    }
}
