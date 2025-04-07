package PlayFair;

import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",12345);
        System.out.println("Client is connected");
        PlayfairCipher.setKey("KEYWORD");
        String encrypted = PlayfairCipher.encrypt("SIDDHANT");
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(encrypted);
        System.out.println("Encrypted text is sent " + encrypted);
        socket.close();

    }
}
