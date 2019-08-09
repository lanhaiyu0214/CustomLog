package com.appo.commonlib; 


/*
 * Copyright (C) Appotronics
 * Author name:
 *		Lanhaiyu
 * Author Email:
 *      306740439@qq.com
 * Create Time:
 * 		2018年8月23日 下午6:27:35 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
public class UtilFuc {

	public static int byteToInt(byte b) {
		// Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
		return b & 0xFF;
	}
	
	public static byte intToByte(int x) {
		return (byte) x;  
	}   
	
	private static final char[] UPPER_CASE_DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z'
    };
	public static String byteToHexString(byte b) {
        char[] digits = UPPER_CASE_DIGITS;
        char[] buf = new char[2]; // We always want two digits.
        buf[0] = digits[(b >> 4) & 0xf];
        buf[1] = digits[b & 0xf];
        return new String(buf,0, 2);
    }
	
	public static String bytesToHexString(byte[] bytes) {
        char[] digits = UPPER_CASE_DIGITS;
        char[] buf = new char[bytes.length * 2];
        int c = 0;
        for (byte b : bytes) {
            buf[c++] = digits[(b >> 4) & 0xf];
            buf[c++] = digits[b & 0xf];
        }
        return new String(buf);
    }
	
	public static String bytesToHexString2(byte[] bytes) {
        char[] digits = UPPER_CASE_DIGITS;
        char[] buf = new char[bytes.length * 3];
        int c = 0;
        for (byte b : bytes) {
            buf[c++] = digits[(b >> 4) & 0xf];
            buf[c++] = digits[b & 0xf];
            buf[c++] = 32;
        }
        return new String(buf);
    }
	
	public static byte[] intToByteArray(int a) {
		return new byte[] {  
		        (byte) ((a >> 24) & 0xFF),  
		        (byte) ((a >> 16) & 0xFF),     
		        (byte) ((a >> 8) & 0xFF),     
		        (byte) (a & 0xFF)  
		    };  
	}  
	/*
	 * The size of bytes must be 4. otherwise return 0.
	 */
	public static int ByteArrayToInt(byte[] bytes) {
		if(bytes.length != 4){
			return 0;
		}
		return   bytes[3] & 0xFF |  
	            (bytes[2] & 0xFF) << 8 |  
	            (bytes[1] & 0xFF) << 16 |  
	            (bytes[0] & 0xFF) << 24;    
	} 
	
	public static  int CharToInt(char c) {
            int  intNum = c - '0';
            return intNum;
    }
	
	public static  byte[] ShortToBytes(short mshort) {
		 byte[] mbytes = new byte[2];
		 mbytes[0] = (byte)(mshort >> 8);
		 mbytes[1] = (byte)(mshort & 0x00FF);
		 return mbytes;
	}
	
}