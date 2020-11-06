package com.market.servicemarket.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class HashUtil {

    public static String hashString(String st) throws Exception{

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(st.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();

        return myHash;

    }



}
