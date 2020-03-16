package com.anc.ancprime.views.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;




/**
 * Created by User on 3/11/2020.
 */
public class MyXAxisValueFormatter extends ValueFormatter {

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        String result = "";
        switch ((int)value){
            case 0:
                result = "Jan";
                break;
            case 1:
                result = "Feb";
                break;
            case 2:
                result = "Mar";
                break;
            case 3:
                result = "Apr";
                break;
            case 4:
                result = "May";
                break;
            case 5:
                result = "Jun";
                break;
            case 6:
                result = "July";
                break;
            case 7:
                result = "Aug";
                break;
            case 8:
                result = "Sep";
                break;
            case 9:
                result = "Oct";
                break;
            case 10:
                result = "Nov";
                break;
            case 11:
                result = "Dec";
                break;

        }
        return result;
    }

}
