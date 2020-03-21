package com.anc.ancprime.views.utils;

import com.anc.ancprime.data.model.salesFlow.SalesData;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;




/**
 * Created by User on 3/11/2020.
 */
public class MyXAxisValueFormatter extends ValueFormatter {

    private List<SalesData> salesDataList;




    public MyXAxisValueFormatter(List<SalesData> salesDataList) {
        this.salesDataList = salesDataList;
    }




    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        int index =  (int) value;
        String result = getMonthName(salesDataList.get(index).getMonth());
        return result;
    }


    private String getMonthName(int value){
        String result = "";
        switch (value) {
            case 1:
                result = "Jan";
                break;
            case 2:
                result = "Feb";
                break;
            case 3:
                result = "Mar";
                break;
            case 4:
                result = "Apr";
                break;
            case 5:
                result = "May";
                break;
            case 6:
                result = "Jun";
                break;
            case 7:
                result = "July";
                break;
            case 8:
                result = "Aug";
                break;
            case 9:
                result = "Sep";
                break;
            case 10:
                result = "Oct";
                break;
            case 11:
                result = "Nov";
                break;
            case 12:
                result = "Dec";
                break;

        }
        return result;
    }

}
