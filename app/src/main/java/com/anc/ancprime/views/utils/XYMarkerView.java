package com.anc.ancprime.views.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.anc.ancprime.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;




/**
 * Created by User on 3/12/2020.
 */
@SuppressLint("ViewConstructor")
public class XYMarkerView extends MarkerView {

    private final TextView tvContent;
    private final ValueFormatter xAxisValueFormatter;

    private final DecimalFormat format;




    public XYMarkerView(Context context, ValueFormatter xAxisValueFormatter) {
        super(context, R.layout.layout_marker_view);

        this.xAxisValueFormatter = xAxisValueFormatter;
        tvContent = findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
    }




    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String productName = "";
        if(e.getX()==0){
            productName = "Vacuum Blood Collection Serum Tube";
        }
        else if(e.getX()==1){
            productName = "Vacuum Blood Collection PT Tube";
        }
        if(e.getX()==2){
            productName = "Vacuum Blood Collection Heparin Tube";
        }
        if(e.getX()==3){
            productName = "Vacuum Blood Collection Glucose Tube";
        }
        if(e.getX()==4){
            productName = "Vacuum Blood Collection ESR Tube";
        }
        if(e.getX()==5){
            productName = "Vacuum Blood Collection ESR Tube";
        }
        tvContent.setText(productName+" Amount: "+format.format(e.getY()));

        super.refreshContent(e, highlight);
    }




    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

}
