package com.anc.ancprime.views.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.anc.ancprime.R;
import com.anc.ancprime.data.model.products.TopFiveProducts;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.List;




/**
 * Created by User on 3/12/2020.
 */
@SuppressLint("ViewConstructor")
public class XYMarkerViewTopProducts extends MarkerView {

    private final TextView tvContent;
    private final ValueFormatter xAxisValueFormatter;
    private final List<TopFiveProducts> topFiveProductList;

    private final DecimalFormat format;




    public XYMarkerViewTopProducts(Context context, ValueFormatter xAxisValueFormatter, List<TopFiveProducts> topFiveProductList) {
        super(context, R.layout.layout_marker_view);

        this.xAxisValueFormatter = xAxisValueFormatter;
        this.topFiveProductList = topFiveProductList;
        tvContent = findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
    }




    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        int index = (int) e.getX() - 1;
        String productName = topFiveProductList.get(index).getProName();
        tvContent.setText(productName + "\n\nQuantity: " + format.format(e.getY()));

        super.refreshContent(e, highlight);
    }




    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

}
