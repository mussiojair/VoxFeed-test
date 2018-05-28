package com.mussiocardenas.voxfeed.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class StringsFunctions {

    public static String capitalizeFirstCharacter( String original ){
        String modified = "";
        String[] words = original.split(" ");
        StringBuilder sb = new StringBuilder();

        for(String word : words){
            String tmp = String.valueOf(word.charAt(0)).toUpperCase() + word.substring( word.length() > 1 ? word.length() - (word.length() - 1) : word.length() );
            sb.append(tmp + " ");
        }
        modified = sb.toString();
        return modified;
    }


    public static String formatDecimal( int decimal ){

        String modified = "";
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setGroupingSeparator('\'');
        DecimalFormat df = new DecimalFormat( "#,###,###,###,##0", unusualSymbols );
        modified = df.format(decimal);
        // transformation
        StringBuilder sb = new StringBuilder(modified);
        modified = sb.reverse().toString().replaceFirst("'", ",");
        sb = new StringBuilder(modified);
        return sb.reverse().toString();
    }

    public static String formatCurrency(float number) {

        float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("$ %5.2f", number); // sdb
        } else {
            return String.format("$ %5.2f", number); // dj_segfault
        }

    }
}
