package geomex.utils;

/**
 * <PRE>
 * 파일명    : Base64Util.java
 * 파일설명 : 클라이언트 요청을 요청별로 분류하여 각 핸들러에게 작업을 전달한다.
 * 수정이력 : 
 *       2013. 6. 19.  김경호  : 최초작성
 * 
 * </PRE>
 * 
 * @author 김경호
 * 
 */
public class Base64Util {

    private static byte[] mBase64EncMap;
    private static byte[] mBase64DecMap;
    static {
        byte[] base64Map = { (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D',
                (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I',
                (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N',
                (byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S',
                (byte) 'T', (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X',
                (byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c',
                (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h',
                (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm',
                (byte) 'n', (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r',
                (byte) 's', (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w',
                (byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1',
                (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
                (byte) '7', (byte) '8', (byte) '9', (byte) '+', (byte) '/' };
        mBase64EncMap = base64Map;
        mBase64DecMap = new byte[128];
        for (int i = 0; i < mBase64EncMap.length; i++) {
            mBase64DecMap[mBase64EncMap[i]] = (byte) i;
        }
    }

    private Base64Util() {}

    /**
     * aData를 Base64-Encoding를 이용해서 Encode한다 RFC-2045 (Section 6.8).
     * 
     * @param aData 인코딩할 데이터
     * @return String : 인코딩 결과 문자열
     * @exception IllegalArgumentException if NULL or empty array is passed
     */
    public static String base64Encode(byte[] aData) {
        if ((aData == null) || (aData.length == 0)) {
            throw new IllegalArgumentException(
                "Can not encode NULL or empty byte array.");
        }

        byte buf[] = new byte[((aData.length + 2) / 3) * 4];

        // 3-byte to 4-byte conversion
        int srcIndex, destIndex;
        for (srcIndex = 0, destIndex = 0; srcIndex < aData.length - 2; srcIndex += 3) {
            buf[destIndex++] = mBase64EncMap[(aData[srcIndex] >>> 2) & 077];
            buf[destIndex++] = mBase64EncMap[(aData[srcIndex + 1] >>> 4) & 017
                | (aData[srcIndex] << 4) & 077];
            buf[destIndex++] = mBase64EncMap[(aData[srcIndex + 2] >>> 6) & 003
                | (aData[srcIndex + 1] << 2) & 077];
            buf[destIndex++] = mBase64EncMap[aData[srcIndex + 2] & 077];
        }

        // Convert the last 1 or 2 bytes
        if (srcIndex < aData.length) {
            buf[destIndex++] = mBase64EncMap[(aData[srcIndex] >>> 2) & 077];
            if (srcIndex < aData.length - 1) {
                buf[destIndex++] = mBase64EncMap[(aData[srcIndex + 1] >>> 4)
                    & 017 | (aData[srcIndex] << 4) & 077];
                buf[destIndex++] = mBase64EncMap[(aData[srcIndex + 1] << 2) & 077];
            } else {
                buf[destIndex++] = mBase64EncMap[(aData[srcIndex] << 4) & 077];
            }
        }

        // Add padding to the end of encoded data
        while (destIndex < buf.length) {
            buf[destIndex] = (byte) '=';
            destIndex++;
        }

        String result = new String(buf);
        return result;
    }

    /**
     * Base64로 인코딩된 데이터를 디코딩 한다. RFC-2045 (Section 6.8).
     * 
     * @param aData 디코딩 할 데이터.
     * @return byte[] : 디코딩 결과 데이터
     * @exception IllegalArgumentException if NULL or empty data is passed
     */
    public static byte[] base64Decode(String aData) {
        if ((aData == null) || (aData.length() == 0)) {
            throw new IllegalArgumentException(
                "Can not decode NULL or empty string.");
        }

        byte[] data = aData.getBytes();

        // Skip padding from the end of encoded data
        int tail = data.length;
        while (data[tail - 1] == '=') {
            tail--;
        }

        byte buf[] = new byte[tail - data.length / 4];

        // ASCII-printable to 0-63 conversion
        for (int i = 0; i < data.length; i++) {
            data[i] = mBase64DecMap[data[i]];
        }

        // 4-byte to 3-byte conversion
        int srcIndex, destIndex;
        for (srcIndex = 0, destIndex = 0; destIndex < buf.length - 2; srcIndex += 4, destIndex += 3) {
            buf[destIndex] = (byte) (((data[srcIndex] << 2) & 255) | ((data[srcIndex + 1] >>> 4) & 003));
            buf[destIndex + 1] = (byte) (((data[srcIndex + 1] << 4) & 255) | ((data[srcIndex + 2] >>> 2) & 017));
            buf[destIndex + 2] = (byte) (((data[srcIndex + 2] << 6) & 255) | (data[srcIndex + 3] & 077));
        }

        // Handle last 1 or 2 bytes
        if (destIndex < buf.length) {
            buf[destIndex] = (byte) (((data[srcIndex] << 2) & 255) | ((data[srcIndex + 1] >>> 4) & 003));
        }
        if (++destIndex < buf.length) {
            buf[destIndex] = (byte) (((data[srcIndex + 1] << 4) & 255) | ((data[srcIndex + 2] >>> 2) & 017));
        }

        return buf;
    }
}
