package Chat.service;

import lombok.RequiredArgsConstructor;

import java.nio.ByteBuffer;
import java.util.ArrayList;

@RequiredArgsConstructor
public class ConvertService {

    private static final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

    public static byte[] longToBytes(long x) {
        byte[] bytes;
        byte[] xor = new byte[buffer.array().length];
        buffer.putLong(0, x);
        System.out.println("NR byte: "+buffer.array().length);
        bytes = buffer.array();

        for (int i = 0; i < buffer.array().length-1; i++) {
            System.out.print(" "+ bytes[i]);
            xor[i] = (byte)((bytes[i] ^ bytes[i+1]) & 0x000000ff);
        }
        System.out.print(" "+ bytes[buffer.array().length-1]);
        System.out.println();
        System.out.println("XOR:");

        for (int i = 0; i < bytes.length; i++) {
            System.out.print(" "+ xor[i]);
        }

        return buffer.array();

    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

}
