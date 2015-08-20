package org.bmsource.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils
{
    public static String encodeSHA256(String value)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            value = new sun.misc.BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return value;
    }
}
