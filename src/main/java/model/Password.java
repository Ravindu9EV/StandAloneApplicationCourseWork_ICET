package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        //INSTANCE FOR HASHING
        MessageDigest md=MessageDigest.getInstance("SHA-512");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
        //
    }

    public static String toHexString(byte[] hash){
        //convert byte[] to digest
        BigInteger number=new BigInteger(1,hash);

        // convert digest into hex
        StringBuilder hexString =new StringBuilder(number.toString(16));

        while (hexString.length()<32){
            hexString.insert(0,'0');
        }
        return hexString.toString();
    }
}
