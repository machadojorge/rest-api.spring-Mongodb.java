package com.workshop.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    public static Date converDate(String textDate, Date defaultDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return sdf.parse(textDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return defaultDate;
        }

    }
    
}
