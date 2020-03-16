package com.anc.ancprime.views.activities;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anc.ancprime.R;
import com.anc.ancprime.data.constants.AppConstants;
import com.anc.ancprime.data.model.ApiResponse;
import com.anc.ancprime.data.model.summary.LastDay;
import com.anc.ancprime.data.model.summary.LastMonth;
import com.anc.ancprime.data.model.summary.LastWeek;
import com.anc.ancprime.data.model.summary.MyPieChartData;
import com.anc.ancprime.data.model.summary.SalesSummaryResponse;
import com.anc.ancprime.data.model.summary.SummaryChartData;
import com.anc.ancprime.viewModels.MainActivityViewModel;
import com.anc.ancprime.views.adapters.Customer;
import com.anc.ancprime.views.adapters.TopCustomerListAdapter;
import com.anc.ancprime.views.utils.MyNumberFormatter;
import com.anc.ancprime.views.utils.MyXAxisValueFormatter;
import com.anc.ancprime.views.utils.XYMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Fill;
import com.github.mikephil.charting.utils.Utils;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_sales_summary)
    AppCompatTextView tvSalesSummary;
    @BindView(R.id.power_spinner)
    PowerSpinnerView powerSpinner;
    @BindView(R.id.pie_chart)
    AnimatedPieView pieChart;
    @BindView(R.id.tv_sale_value)
    AppCompatTextView tvSaleValue;
    @BindView(R.id.tv_vat_value)
    AppCompatTextView tvVatValue;
    @BindView(R.id.tv_discount_value)
    AppCompatTextView tvDiscountValue;
    @BindView(R.id.tv_account_receivable)
    AppCompatTextView tvAccountReceivable;
    @BindView(R.id.tv_paid_value)
    AppCompatTextView tvPaidValue;
    @BindView(R.id.tv_due_value)
    AppCompatTextView tvDueValue;
    @BindView(R.id.include_layout_dashboard)
    LinearLayoutCompat includeLayoutDashboard;
    @BindView(R.id.guideline_1)
    Guideline guideline1;
    @BindView(R.id.iv_drawer_menu)
    AppCompatImageView ivDrawerMenu;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.iv_graph)
    AppCompatImageView ivGraph;
    @BindView(R.id.iv_options)
    AppCompatImageView ivOptions;
    @BindView(R.id.guideline_2)
    Guideline guideline2;
    @BindView(R.id.sales_report_line_chart)
    LineChart salesReportLineChart;
    @BindView(R.id.top_selling_product_chart)
    BarChart topSellingProductChart;
    @BindView(R.id.least_selling_product_chart)
    BarChart leastSellingProductChart;
    @BindView(R.id.rv_order_status)
    RecyclerView rvOrderStatus;
    @BindView(R.id.rl_details_view)
    NestedScrollView rlDetailsView;
    @BindView(R.id.content)
    MotionLayout content;
    @BindView(R.id.drawer)
    LinearLayoutCompat drawer;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;


    private LineChart mSalesReportChart;
    private BarChart mTopSellingProductChart, mLeastSellingProductChart;
    private MainActivityViewModel viewModel;
    private SummaryChartData summaryChartData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        intiView();
        initViewModel();
    }




    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.loadSalesSummary();
        viewModel.getApiResponse().observe(this, apiResponse -> {
            consumeResponse(apiResponse);
        });
    }




    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                //showProgressBar();
                break;


            case SUCCESS:
                //hideProgressBar();
                SalesSummaryResponse response = (SalesSummaryResponse) apiResponse.data;
                if (response != null && response.getStatus()) {
                    summaryChartData = response.getData();
                    setPieChartData(response.getData().getLastWeek(), AppConstants.SEARCH_CATEGORY_THIS_WEEK);
                } else if (response != null) {
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;


            case ERROR:
                //hideProgressBar();
                Toast.makeText(getApplicationContext(), "Network error happens!", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }




    private void setPieChartData(MyPieChartData myPieChartData, String category) {
        float sales = 0f;
        float paid = 0f;
        float due = 0f;
        float discount = 0f;
        float vat = 0f;
        float receivable = 0f;



        if (category.equalsIgnoreCase(AppConstants.SEARCH_CATEGORY_TODAY)) {
            LastDay lastDay = (LastDay) myPieChartData;
            sales = lastDay.getTotalSubTotal();
            due = lastDay.getTotalDue();
            discount = lastDay.getTotalDiscount();
            paid = lastDay.getTotalPayable() - lastDay.getTotalDue();
            vat = lastDay.getTotalVat();
            receivable = lastDay.getTotalPayable();
        } else if (category.equalsIgnoreCase(AppConstants.SEARCH_CATEGORY_THIS_WEEK)) {
            LastWeek lastWeek = (LastWeek) myPieChartData;
            sales = lastWeek.getTotalSubTotal();
            due = lastWeek.getTotalDue();
            discount = lastWeek.getTotalDiscount();
            paid = lastWeek.getTotalPayable() - lastWeek.getTotalDue();
            vat = lastWeek.getTotalVat();
            receivable = lastWeek.getTotalPayable();
        } else if (category.equalsIgnoreCase(AppConstants.SEARCH_CATEGORY_THIS_MONTH)) {
            LastMonth lastMonth = (LastMonth) myPieChartData;
            sales = lastMonth.getTotalSubTotal();
            due = lastMonth.getTotalDue();
            discount = lastMonth.getTotalDiscount();
            paid = lastMonth.getTotalPayable() - lastMonth.getTotalDue();
            vat = lastMonth.getTotalVat();
            receivable = lastMonth.getTotalPayable();
        }

        tvSaleValue.setText("Sales " + MyNumberFormatter.format(sales));
        tvVatValue.setText("Vat " + MyNumberFormatter.format(vat));
        tvDiscountValue.setText("Discount " + MyNumberFormatter.format(discount));

        tvAccountReceivable.setText("Receivable " + MyNumberFormatter.format(receivable));
        tvPaidValue.setText("Paid "+MyNumberFormatter.format(paid));
        tvDueValue.setText("Due "+MyNumberFormatter.format(due));


        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(180) // initial angular offset
                .addData(new SimplePieInfo(sales, getResources().getColor(R.color.pie_color_1), "" + MyNumberFormatter.format(sales))) // Data (bean IPieInfo implement interface)
                .addData(new SimplePieInfo(vat, getResources().getColor(R.color.pie_color_2), "" + MyNumberFormatter.format(vat)))
                .addData(new SimplePieInfo(discount, getResources().getColor(R.color.pie_color_3), "" + MyNumberFormatter.format(discount)))
                .canTouch(true)
                .drawText(true)
                .animOnTouch(true)
                .textSize(36)
                .pieRadius(100)
                .strokeWidth(35)
                .duration(500);


        pieChart.applyConfig(config);
        pieChart.start();
    }




    private void intiView() {
        initDrawerLayout();
        setSalesReportChart();
        setTopSellingProducts();
        setLeastSellingProducts();
        initAdapter();
        initSpinner();
    }




    private void initDrawerLayout() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.color_trim));

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(slideX);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        ivDrawerMenu.setOnClickListener(view -> drawerLayout.openDrawer(Gravity.LEFT));
    }




    private void initSpinner() {
        powerSpinner.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (position, item) -> {
            if (position == 0) {
                if (summaryChartData != null && summaryChartData.getLastWeek() != null) {
                    setPieChartData(summaryChartData.getLastWeek(), AppConstants.SEARCH_CATEGORY_THIS_WEEK);
                }

            } else if (position == 1) {
                if (summaryChartData != null && summaryChartData.getLastDay() != null) {
                    setPieChartData(summaryChartData.getLastDay(), AppConstants.SEARCH_CATEGORY_TODAY);
                }

            } else {
                if (summaryChartData != null && summaryChartData.getLastMonth() != null) {
                    setPieChartData(summaryChartData.getLastMonth(), AppConstants.SEARCH_CATEGORY_THIS_MONTH);
                }

            }
        });
    }




    private void setSalesReportChart() {
        mSalesReportChart = findViewById(R.id.sales_report_line_chart);

        {
            mSalesReportChart.setBackgroundColor(Color.WHITE);
            mSalesReportChart.getDescription().setEnabled(false);
            mSalesReportChart.setTouchEnabled(true);
            mSalesReportChart.setDrawGridBackground(false);
            mSalesReportChart.getAxisLeft().setDrawGridLines(false);
            mSalesReportChart.getXAxis().setDrawGridLines(false);
            mSalesReportChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

            mSalesReportChart.setDragEnabled(true);
            mSalesReportChart.setScaleEnabled(true);
            mSalesReportChart.setPinchZoom(true);
        }

        XAxis xAxis;
        {
            xAxis = mSalesReportChart.getXAxis();
            xAxis.disableAxisLineDashedLine();
            xAxis.disableGridDashedLine();
        }

        YAxis yAxis;
        {
            yAxis = mSalesReportChart.getAxisLeft();
            mSalesReportChart.getAxisRight().setEnabled(false);
            yAxis.setAxisMaximum(500000f);
            yAxis.setAxisMinimum(0f);
            yAxis.setValueFormatter(new LargeValueFormatter());
        }


        setSalesReportData(12, 500000);
        mSalesReportChart.animateX(500);
        Legend l = mSalesReportChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
    }




    private void setSalesReportData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range);
            values.add(new Entry(i, val, null));
        }

        LineDataSet set1;

        if (mSalesReportChart.getData() != null &&
                mSalesReportChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mSalesReportChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            mSalesReportChart.getData().notifyDataChanged();
            mSalesReportChart.notifyDataSetChanged();
        } else {

            set1 = new LineDataSet(values, null);
            set1.setDrawIcons(false);


            set1.setColor(getResources().getColor(R.color.color_line_graph_border));
            set1.setCircleColor(getResources().getColor(R.color.color_line_graph_border));
            set1.setCircleRadius(4f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setValueTextColor(getResources().getColor(R.color.color_text_normal_black));
            set1.setLineWidth(2f);
            set1.disableDashedLine();
            set1.setDrawFilled(true);
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_gradient);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return mSalesReportChart.getAxisLeft().getAxisMinimum();
                }
            });

            set1.setValueFormatter(new LargeValueFormatter());


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);


            LineData data = new LineData(dataSets);
            mSalesReportChart.setData(data);
            mSalesReportChart.getXAxis().setValueFormatter(new MyXAxisValueFormatter());
            mSalesReportChart.getXAxis().setGranularity(1f);
            mSalesReportChart.getXAxis().setTextColor(getResources().getColor(R.color.color_text_light_gray_1));
            mSalesReportChart.getAxisLeft().setTextColor(getResources().getColor(R.color.color_text_light_gray_1));


            List<ILineDataSet> sets = mSalesReportChart.getData()
                    .getDataSets();

            for (ILineDataSet iSet : sets) {

                LineDataSet set = (LineDataSet) iSet;
                set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                        ? LineDataSet.Mode.LINEAR
                        : LineDataSet.Mode.CUBIC_BEZIER);
            }
            mSalesReportChart.invalidate();
        }


    }




    private void setTopSellingProducts() {
        mTopSellingProductChart = findViewById(R.id.top_selling_product_chart);

        mTopSellingProductChart.setDrawBarShadow(false);
        mTopSellingProductChart.setDrawValueAboveBar(true);

        mTopSellingProductChart.getDescription().setEnabled(false);
        mTopSellingProductChart.setPinchZoom(true);

        mTopSellingProductChart.setDrawGridBackground(false);


        XAxis xAxis = mTopSellingProductChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5);
        xAxis.setTextColor(R.color.color_text_light_gray_1);


        YAxis leftAxis = mTopSellingProductChart.getAxisLeft();
        leftAxis.setLabelCount(6, true);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(R.color.color_text_light_gray_1);

        YAxis rightAxis = mTopSellingProductChart.getAxisRight();
        rightAxis.setEnabled(false);

        XYMarkerView mv = new XYMarkerView(this, new LargeValueFormatter());
        mv.setChartView(mTopSellingProductChart); // For bounds control
        mTopSellingProductChart.setMarker(mv);

        setDataForTopSellingProducts(5, 500000f);
    }




    private void setDataForTopSellingProducts(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));

            if (Math.random() * 100 < 25) {
                values.add(new BarEntry(i, val, null));
            } else {
                values.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (mTopSellingProductChart.getData() != null &&
                mTopSellingProductChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mTopSellingProductChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mTopSellingProductChart.getData().notifyDataChanged();
            mTopSellingProductChart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "");

            set1.setDrawIcons(false);

            int startColor = ContextCompat.getColor(this, R.color.color_green);
            int endColor = ContextCompat.getColor(this, R.color.color_vertical_border_light_green);


            List<Fill> gradientFills = new ArrayList<>();
            gradientFills.add(new Fill(endColor, startColor));


            set1.setFills(gradientFills);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTextColor(R.color.color_text_normal_black);
            data.setBarWidth(0.9f);
            data.setValueFormatter(new LargeValueFormatter());

            mTopSellingProductChart.setData(data);
        }
        mTopSellingProductChart.invalidate();
    }




    private void setLeastSellingProducts() {
        mLeastSellingProductChart = findViewById(R.id.least_selling_product_chart);

        mLeastSellingProductChart.setDrawBarShadow(false);
        mLeastSellingProductChart.setDrawValueAboveBar(true);

        mLeastSellingProductChart.getDescription().setEnabled(false);
        mLeastSellingProductChart.setPinchZoom(true);

        mLeastSellingProductChart.setDrawGridBackground(false);


        XAxis xAxis = mLeastSellingProductChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5);
        xAxis.setTextColor(R.color.color_text_light_gray_1);


        YAxis leftAxis = mLeastSellingProductChart.getAxisLeft();
        leftAxis.setLabelCount(6, true);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(R.color.color_text_light_gray_1);

        YAxis rightAxis = mLeastSellingProductChart.getAxisRight();
        rightAxis.setEnabled(false);

        XYMarkerView mv = new XYMarkerView(this, new LargeValueFormatter());
        mv.setChartView(mLeastSellingProductChart); // For bounds control
        mLeastSellingProductChart.setMarker(mv);

        setDataForLeastSellingProducts(5, 500000f);
    }




    private void setDataForLeastSellingProducts(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));

            if (Math.random() * 100 < 25) {
                values.add(new BarEntry(i, val, null));
            } else {
                values.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (mLeastSellingProductChart.getData() != null &&
                mLeastSellingProductChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mLeastSellingProductChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLeastSellingProductChart.getData().notifyDataChanged();
            mLeastSellingProductChart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "");

            set1.setDrawIcons(false);

            int startColor = ContextCompat.getColor(this, R.color.color_green);
            int endColor = ContextCompat.getColor(this, R.color.color_vertical_border_light_green);


            List<Fill> gradientFills = new ArrayList<>();
            gradientFills.add(new Fill(endColor, startColor));


            set1.setFills(gradientFills);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTextColor(R.color.color_text_normal_black);
            data.setBarWidth(0.9f);
            data.setValueFormatter(new LargeValueFormatter());

            mLeastSellingProductChart.setData(data);
        }
        mLeastSellingProductChart.invalidate();
    }




    private void initAdapter() {
        RecyclerView recyclerView = findViewById(R.id.rv_order_status);
        List<Customer> mArrayList = new ArrayList<>();
        mArrayList.add(new Customer("United Hospital", "", "Gulshan", "90000"));
        mArrayList.add(new Customer("Apollo Hospitals", "", "Gulshan", "70000"));
        mArrayList.add(new Customer("Square Hospital", "", "Panthopath", "60000"));
        mArrayList.add(new Customer("Labaid", "", "Gulshan", "50000"));
        mArrayList.add(new Customer("Ibn Sina Hospital", "", "Dhanmondi", "55000"));


        TopCustomerListAdapter mAdapter = new TopCustomerListAdapter(getApplicationContext(), mArrayList, new TopCustomerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }


}
