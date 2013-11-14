import javax.bluetooth.LocalDevice;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 11/14/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class WaitThread implements Runnable{
    public WaitThread(){

    }

    @Override
    public void run() {
        waitForConnection();
    }

    private void waitForConnection() {
        LocalDevice local = null;

        StreamConnectionNotifier notifier;
        StreamConnection connection = null;

        try {
            local = LocalDevice.getLocalDevice();

        }   catch (Exception e) {
            e.printStackTrace();
        }


    }


}
