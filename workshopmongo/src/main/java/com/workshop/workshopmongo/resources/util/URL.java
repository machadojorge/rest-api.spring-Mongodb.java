package com.workshop.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

    // this method receive a text encoded, because we passe to parameter, through the Url the title of post, and after this, we needs decode this parameter
    // for that, we use this class
    public static String decodeParam(String text)
    {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
    
}
