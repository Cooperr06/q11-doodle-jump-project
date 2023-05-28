package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator
{
    public static String sha256(String input)
    {
        try
        {
            // hashes the given input
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte hashByte : hashBytes)
            {
                String hex = Integer.toHexString(0xff & hashByte);
                // ensure the resulting hex string has always two characters
                if (hex.length() == 1)
                {
                    hexStringBuilder.append("0");
                }
                hexStringBuilder.append(hex);
            }
            return hexStringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
