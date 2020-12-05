package clientcaro;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddress {

    public static String getIpAddress(){
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip.getHostAddress().toString();
    }
}
