package DSS;

import java.io.DataInput;
import java.io.DataInputStream;
import java.math.BigInteger;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(12345);
        Socket socket = server.accept();
        DataInputStream in = new DataInputStream(socket.getInputStream());
        String message = in.readUTF();
        BigInteger r = new BigInteger(in.readUTF());
        BigInteger s = new BigInteger(in.readUTF());

        boolean valid = DSSUtils.verify(message,r,s);
        System.out.println(valid);
        socket.close();
        server.close();

    }
}
