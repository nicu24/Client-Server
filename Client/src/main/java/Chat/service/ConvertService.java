package Chat.service;

import lombok.RequiredArgsConstructor;

import java.nio.ByteBuffer;
import java.util.ArrayList;

@RequiredArgsConstructor
public class ConvertService {

    private static final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

    public static byte[] longToBytes(long x) {
        byte[] bytes;
        buffer.putLong(0, x);
        System.out.println(buffer.array().length);
        bytes = buffer.array();
        for (int i = 0; i < buffer.array().length; i++) {
            System.out.print(buffer.get());
        }
        return buffer.array();
//        ArrayList<Byte> bytes = new ArrayList<Byte>();
//        while (l != 0) {
//            bytes.add((byte) (l % (0xff + 1)));
//            l = l >> 8;
//        }
//        byte[] bytesp = new byte[bytes.size()];
//        for (int i = bytes.size() - 1, j = 0; i >= 0; i--, j++) {
//            bytesp[j] = bytes.get(i);
//        }
//        for (int i = 0; i < bytes.size(); i++) {
//            System.out.println(bytesp[i]);
//        }
//        return bytesp;
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

}
