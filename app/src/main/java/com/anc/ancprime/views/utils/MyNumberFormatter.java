package com.anc.ancprime.views.utils;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;




/**
 * Created by User on 3/16/2020.
 */
public class MyNumberFormatter {

    private static final NavigableMap<Float, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000f, "k");
        suffixes.put(1_000_000f, "m");
        suffixes.put(1_000_000_000f, "G");
        suffixes.put(1_000_000_000_000f, "T");
        suffixes.put(1_000_000_000_000_000f, "P");
        suffixes.put(1_000_000_000_000_000_000f, "E");
    }

    public static String format(float value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        DecimalFormat formatter = new DecimalFormat("#");

        value = Float.valueOf(formatter.format(value));
        Log.d("APiTesting", "Value " + value);
        if (value == Float.MIN_VALUE) return format(Float.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Float.toString(value); //deal with easy case

        Map.Entry<Float, String> e = suffixes.floorEntry(value);
        Float divideBy = e.getKey();
        String suffix = e.getValue();

        Float truncated = value / (divideBy / 10); //the number part of the output times 10
        truncated = Float.valueOf(formatter.format(truncated));
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + " " + suffix : (truncated / 10) + " " + suffix;
    }

}
