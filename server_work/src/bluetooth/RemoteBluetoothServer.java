package bluetooth;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 11/14/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
// Built on demonstration code found here: http://luugiathuy.com/2011/02/android-java-bluetooth/comment-page-2/
public class RemoteBluetoothServer {
    public static void kkmain(String[] args) {
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();

    }
}
