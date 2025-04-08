package DSS;

import java.io.DataOutputStream;
import java.math.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",12345);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        String message = "Hello";
        BigInteger[] signature = DSSUtils.sign(message);
        out.writeUTF(message);
        out.writeUTF(signature[0].toString());
        out.writeUTF(signature[1].toString());;
        socket.close();
        
    }
}
