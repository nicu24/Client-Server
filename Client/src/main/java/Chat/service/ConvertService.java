package Chat.service;

import lombok.RequiredArgsConstructor;

import java.nio.ByteBuffer;
import java.util.ArrayList;

@RequiredArgsConstructor
public class ConvertService {

    private static final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    private static final ByteBuffer buffer2 = ByteBuffer.allocate(Long.BYTES);

    public static byte[] longToBytes(long x, long y) {

        byte[] xBytes;
        byte[] yBytes;
        buffer.putLong(0, x);
        xBytes = buffer.array();

        buffer2.putLong(0,y);
        yBytes= buffer2.array();

        byte[] xor = new byte[buffer.array().length];

        System.out.println(" xByte");
        for (int i = 0; i < xBytes.length; i++) {
            System.out.print(" "+xBytes[i]);
        }

        System.out.println("\n yByte");
        for (int i = 0; i < yBytes.length; i++) {
            System.out.print(" "+yBytes[i]);
        }

        for (int i = 0; i < buffer.array().length; i++) {
            xor[i] = (byte)((xBytes[i] ^ yBytes[i]) & 0x000000ff);
        }
        System.out.println();
        System.out.println("XOR:");

        for (int i = 0; i < yBytes.length; i++) {
            System.out.print(" "+ xor[i]);
        }
        buffer.clear();
        buffer2.clear();
        return xor;

    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

}
