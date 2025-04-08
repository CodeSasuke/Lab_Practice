package SHA;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Padding {
    public static byte[] padMessage(String message) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        int originalLength = messageBytes.length;
        long messageBitLength = (long) originalLength * 8;

        // Add 1 byte: 0x80 (10000000 in binary)
        byte[] withOneBit = Arrays.copyOf(messageBytes, originalLength + 1);
        withOneBit[originalLength] = (byte) 0x80;

        // Calculate padding size
        int totalLength = withOneBit.length;
        int mod = totalLength % 128;
        int paddingLength = (mod <= 112) ? (112 - mod) : (240 - mod);

        // Add padding zeroes
        byte[] padded = Arrays.copyOf(withOneBit, withOneBit.length + paddingLength + 16);

        // Append 128-bit length (we only use 64 bits here, rest zeros)
        byte[] lengthBytes = ByteBuffer.allocate(16).putLong(8, messageBitLength).array();
        System.arraycopy(lengthBytes, 0, padded, padded.length - 16, 16);

        return padded;
    }
}