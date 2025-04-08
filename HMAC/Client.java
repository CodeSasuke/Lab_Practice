package HMAC;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        String msg = "NarashresthaRocks!";
        String key = "secretkey123456";

        byte[] hmac = HMACUtils.hmac(key.getBytes(), msg.getBytes());

        Socket socket = new Socket("localhost", 5000);
        OutputStream out = socket.getOutputStream();
        out.write(hmac);

        System.out.println("HMAC sent.");
        socket.close();
    }
}
