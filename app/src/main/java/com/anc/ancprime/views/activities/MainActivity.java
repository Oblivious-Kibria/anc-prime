package com.anc.ancprime.views.activities;

import android.content.Intent;
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
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anc.ancprime.R;
import com.anc.ancprime.data.constants.AppConstants;
import com.anc.ancprime.data.model.ApiResponse;
import com.anc.ancprime.data.model.customer.CustomerData;
import com.anc.ancprime.data.model.customer.TopCustomersResponse;
import com.anc.ancprime.data.model.products.LeastFiveProducts;
import com.anc.ancprime.data.model.products.ProductSummaryResponse;
import com.anc.ancprime.data.model.products.TopFiveProducts;
import com.anc.ancprime.data.model.salesFlow.SalesData;
import com.anc.ancprime.data.model.salesFlow.SalesFlowSummary;
import com.anc.ancprime.data.model.summary.LastDay;
import com.anc.ancprime.data.model.summary.LastMonth;
import com.anc.ancprime.data.model.summary.LastWeek;
import com.anc.ancprime.data.model.summary.MyPieChartData;
import com.anc.ancprime.data.model.summary.SalesSummaryResponse;
import com.anc.ancprime.data.model.summary.SummaryChartData;
import com.anc.ancprime.viewModels.MainActivityViewModel;
import com.anc.ancprime.views.adapters.TopCustomerListAdapter;
import com.anc.ancprime.views.utils.MyNumberFormatter;
import com.anc.ancprime.views.utils.MyXAxisValueFormatter;
import com.anc.ancprime.views.utils.XYMarkerViewLeastProducts;
import com.anc.ancprime.views.utils.XYMarkerViewTopProducts;
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




public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    @BindView(R.id.sales_flow_line_chart)
    LineChart salesFlowLineChart;
    @BindView(R.id.top_selling_product_chart)
    BarChart topSellingProductChart;
    @BindView(R.id.least_selling_product_chart)
    BarChart leastSellingProductChart;
    @BindView(R.id.rv_customers)
    RecyclerView rvCustomers;
    @BindView(R.id.rl_details_view)
    NestedScrollView rlDetailsView;
    @BindView(R.id.content)
    MotionLayout content;
    @BindView(R.id.drawer)
    LinearLayoutCompat drawer;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar_shadow_view)
    View toolbarShadowView;
    @BindView(R.id.ll_inventory)
    LinearLayoutCompat llInventory;
    @BindView(R.id.ll_customer)
    LinearLayoutCompat llCustomer;
    @BindView(R.id.ll_cash_due)
    LinearLayoutCompat llCashDue;
    @BindView(R.id.progress_circular_1)
    ContentLoadingProgressBar progressCircular1;
    @BindView(R.id.progress_circular_2)
    ContentLoadingProgressBar progressCircular2;
    @BindView(R.id.progress_circular_3)
    ContentLoadingProgressBar progressCircular3;
    @BindView(R.id.progress_circular_4)
    ContentLoadingProgressBar progressCircular4;
    @BindView(R.id.progress_circular_5)
    ContentLoadingProgressBar progressCircular5;


    private MainActivityViewModel viewModel;
    private SummaryChartData summaryChartData;
    private TopCustomerListAdapter mAdapter;
    private List<CustomerData> mArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        intiView();
        initViewModel();
        initListeners();
    }




    private void intiView() {
        initDrawerLayout();
        initSalesFlowChart();
        initAdapter();
        initSpinner();
    }




    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.loadSalesSummary();
        viewModel.loadProductSummary();
        viewModel.loadSalesFlowSummary();
        viewModel.loadTopCustomers();
        viewModel.getSalesSummaryResponse().observe(this, apiResponse -> {
            consumeResponse(apiResponse);
        });
        viewModel.getProductSummaryResponse().observe(this, apiResponse -> {
            consumeResponse(apiResponse);
        });
        viewModel.getSalesFlowSummaryResponse().observe(this, apiResponse -> {
            consumeResponse(apiResponse);
        });
        viewModel.getTopCustomersResponse().observe(this, apiResponse -> {
            consumeResponse(apiResponse);
        });
    }




    private void initListeners() {
        llInventory.setOnClickListener(this);
        llCustomer.setOnClickListener(this);
        llCashDue.setOnClickListener(this);
    }




    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                switch (apiResponse.requestType) {
                    case AppConstants.REQUEST_TYPE_SALES_SUMMARY:
                        progressCircular1.show();
                        break;
                    case AppConstants.REQUEST_TYPE_SALES_FLOW_SUMMARY:
                        progressCircular2.show();
                        break;
                    case AppConstants.REQUEST_TYPE_PRODUCT_SUMMARY:
                        progressCircular3.show();
                        progressCircular4.show();
                        break;
                    case AppConstants.REQUEST_TYPE_TOP_CUSTOMERS:
                        progressCircular5.show();
                        break;
                }
                break;


            case SUCCESS:
                //hideProgressBar();
                switch (apiResponse.requestType) {
                    case AppConstants.REQUEST_TYPE_SALES_SUMMARY:
                        SalesSummaryResponse salesSummaryResponse = (SalesSummaryResponse) apiResponse.data;
                        if (salesSummaryResponse != null && salesSummaryResponse.getStatus()) {
                            summaryChartData = salesSummaryResponse.getData();
                            setPieChartData(salesSummaryResponse.getData().getLastWeek(), AppConstants.SEARCH_CATEGORY_THIS_WEEK);
                        } else if (salesSummaryResponse != null) {
                            Toast.makeText(this, salesSummaryResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case AppConstants.REQUEST_TYPE_PRODUCT_SUMMARY:
                        ProductSummaryResponse productSummaryResponse = (ProductSummaryResponse) apiResponse.data;
                        if (productSummaryResponse != null && productSummaryResponse.getStatus()) {
                            List<TopFiveProducts> topFiveProducts = productSummaryResponse.getData().getLastMonth().getTop5Product();
                            List<LeastFiveProducts> leastFiveProducts = productSummaryResponse.getData().getLastMonth().getLow5Product();
                            if (topFiveProducts != null && topFiveProducts.size() > 0) {
                                setTopSellingProducts(topFiveProducts);
                            }

                            if (leastFiveProducts != null && leastFiveProducts.size() > 0) {
                                setLeastSellingProducts(leastFiveProducts);
                            }
                        } else if (productSummaryResponse != null) {
                            Toast.makeText(this, productSummaryResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;


                    case AppConstants.REQUEST_TYPE_SALES_FLOW_SUMMARY:
                        SalesFlowSummary salesFlowSummary = (SalesFlowSummary) apiResponse.data;
                        if (salesFlowSummary != null && salesFlowSummary.getStatus()) {
                            if (salesFlowSummary.getData() != null && salesFlowSummary.getData().size() > 0) {
                                setSalesFlowData(salesFlowSummary.getData());
                            }
                        } else if (salesFlowSummary != null) {
                            Toast.makeText(this, salesFlowSummary.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;


                    case AppConstants.REQUEST_TYPE_TOP_CUSTOMERS:
                        TopCustomersResponse topCustomersResponse = (TopCustomersResponse) apiResponse.data;
                        if (topCustomersResponse != null && topCustomersResponse.getStatus()) {
                            if (topCustomersResponse.getData() != null && topCustomersResponse.getData().size() > 0) {
                                mArrayList.clear();
                                mArrayList.addAll(topCustomersResponse.getData());
                                mAdapter.notifyDataSetChanged();
                            }
                        } else if (topCustomersResponse != null) {
                            Toast.makeText(this, topCustomersResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;


            case ERROR:
                switch (apiResponse.requestType) {
                    case AppConstants.REQUEST_TYPE_SALES_SUMMARY:
                        progressCircular1.hide();
                        break;
                    case AppConstants.REQUEST_TYPE_SALES_FLOW_SUMMARY:
                        progressCircular2.hide();
                        break;
                    case AppConstants.REQUEST_TYPE_PRODUCT_SUMMARY:
                        progressCircular3.hide();
                        progressCircular4.hide();
                        break;
                    case AppConstants.REQUEST_TYPE_TOP_CUSTOMERS:
                        progressCircular5.hide();
                        break;
                }
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
        tvPaidValue.setText("Paid " + MyNumberFormatter.format(paid));
        tvDueValue.setText("Due " + MyNumberFormatter.format(due));


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




    /***
     * To show monthly sales data in line graph;
     */
    private void initSalesFlowChart() {
        {
            salesFlowLineChart.setBackgroundColor(Color.WHITE);
            salesFlowLineChart.getDescription().setEnabled(false);
            salesFlowLineChart.setTouchEnabled(true);
            salesFlowLineChart.setDrawGridBackground(false);
            salesFlowLineChart.getAxisLeft().setDrawGridLines(false);
            salesFlowLineChart.getXAxis().setDrawGridLines(false);
            salesFlowLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

            salesFlowLineChart.setDragEnabled(true);
            salesFlowLineChart.setScaleEnabled(true);
            salesFlowLineChart.setPinchZoom(true);
        }


    }




    /***
     * To set data of monthly sales in line graph after network call successful;
     */
    private void setSalesFlowData(List<SalesData> salesDataList) {

        XAxis xAxis;
        {
            xAxis = salesFlowLineChart.getXAxis();
            xAxis.disableAxisLineDashedLine();
            xAxis.disableGridDashedLine();
        }

        YAxis yAxis;
        {
            yAxis = salesFlowLineChart.getAxisLeft();
            salesFlowLineChart.getAxisRight().setEnabled(false);
            yAxis.setAxisMaximum(2500f);
            yAxis.setAxisMinimum(0f);
            yAxis.setValueFormatter(new LargeValueFormatter());
        }


        salesFlowLineChart.animateX(500);
        Legend l = salesFlowLineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);


        int count = salesDataList.size();


        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = salesDataList.get(i).getTotalAmount();
            values.add(new Entry(i, val, null));
        }

        LineDataSet set1;

        if (salesFlowLineChart.getData() != null &&
                salesFlowLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) salesFlowLineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            salesFlowLineChart.getData().notifyDataChanged();
            salesFlowLineChart.notifyDataSetChanged();
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
                    return salesFlowLineChart.getAxisLeft().getAxisMinimum();
                }
            });

            set1.setValueFormatter(new LargeValueFormatter());


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);


            LineData data = new LineData(dataSets);
            salesFlowLineChart.setData(data);
            salesFlowLineChart.getXAxis().setValueFormatter(new MyXAxisValueFormatter(salesDataList));
            salesFlowLineChart.getXAxis().setGranularity(1f);
            salesFlowLineChart.getXAxis().setTextColor(getResources().getColor(R.color.color_text_light_gray_1));
            salesFlowLineChart.getAxisLeft().setTextColor(getResources().getColor(R.color.color_text_light_gray_1));


            List<ILineDataSet> sets = salesFlowLineChart.getData()
                    .getDataSets();

            for (ILineDataSet iSet : sets) {

                LineDataSet set = (LineDataSet) iSet;
                set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                        ? LineDataSet.Mode.LINEAR
                        : LineDataSet.Mode.CUBIC_BEZIER);
            }
            salesFlowLineChart.invalidate();
        }
    }




    /***
     * To set data of five top selling products after network call successful;
     */
    private void setTopSellingProducts(List<TopFiveProducts> topFiveProductList) {
        topSellingProductChart.setDrawBarShadow(false);
        topSellingProductChart.setDrawValueAboveBar(true);
        topSellingProductChart.getDescription().setEnabled(false);
        topSellingProductChart.setPinchZoom(true);
        topSellingProductChart.setDrawGridBackground(false);


        XAxis xAxis = topSellingProductChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(topFiveProductList.size());
        xAxis.setTextColor(R.color.color_text_light_gray_1);


        YAxis leftAxis = topSellingProductChart.getAxisLeft();
        leftAxis.setLabelCount(topFiveProductList.size() + 1, true);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(R.color.color_text_light_gray_1);

        YAxis rightAxis = topSellingProductChart.getAxisRight();
        rightAxis.setEnabled(false);

        XYMarkerViewTopProducts mv = new XYMarkerViewTopProducts(this, new LargeValueFormatter(), topFiveProductList);
        mv.setChartView(topSellingProductChart); // For bounds control
        topSellingProductChart.setMarker(mv);


        /***
         * Setting data for bar chart;
         */
        int start = 1;
        int count = topFiveProductList.size();

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = start; i < start + count; i++) {
            TopFiveProducts topFiveProduct = topFiveProductList.get(i - 1);
            float unitPrice = topFiveProduct.getUnitPrice();
            int quantity = Integer.valueOf(topFiveProduct.getQuantity());
            float totalPrice = unitPrice * quantity;
            values.add(new BarEntry(i, totalPrice));
        }

        BarDataSet set1;

        if (topSellingProductChart.getData() != null &&
                topSellingProductChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) topSellingProductChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            topSellingProductChart.getData().notifyDataChanged();
            topSellingProductChart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "");

            set1.setDrawIcons(false);

            int startColor = ContextCompat.getColor(this, R.color.color_vertical_border_deep_green);
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

            topSellingProductChart.setData(data);
        }
        topSellingProductChart.invalidate();
    }




    /***
     * To set data of five least selling products after network call successful;
     */
    private void setLeastSellingProducts(List<LeastFiveProducts> leastFiveProductList) {
        leastSellingProductChart.setDrawBarShadow(false);
        leastSellingProductChart.setDrawValueAboveBar(true);

        leastSellingProductChart.getDescription().setEnabled(false);
        leastSellingProductChart.setPinchZoom(true);

        leastSellingProductChart.setDrawGridBackground(false);


        XAxis xAxis = leastSellingProductChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(leastFiveProductList.size());
        xAxis.setTextColor(R.color.color_text_light_gray_1);


        YAxis leftAxis = leastSellingProductChart.getAxisLeft();
        leftAxis.setLabelCount(leastFiveProductList.size() + 1, true);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(R.color.color_text_light_gray_1);

        YAxis rightAxis = leastSellingProductChart.getAxisRight();
        rightAxis.setEnabled(false);

        XYMarkerViewLeastProducts mv = new XYMarkerViewLeastProducts(this, new LargeValueFormatter(), leastFiveProductList);
        mv.setChartView(leastSellingProductChart); // For bounds control
        leastSellingProductChart.setMarker(mv);


        /***
         * Setting data for bar chart;
         */
        int start = 1;
        int count = leastFiveProductList.size();
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = start; i < start + count; i++) {
            LeastFiveProducts leastFiveProduct = leastFiveProductList.get(i - 1);
            float unitPrice = leastFiveProduct.getUnitPrice();
            int quantity = Integer.valueOf(leastFiveProduct.getQuantity());
            float totalPrice = unitPrice * quantity;
            values.add(new BarEntry(i, totalPrice));
        }

        BarDataSet set1;

        if (leastSellingProductChart.getData() != null &&
                leastSellingProductChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) leastSellingProductChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            leastSellingProductChart.getData().notifyDataChanged();
            leastSellingProductChart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "");

            set1.setDrawIcons(false);

            int startColor = ContextCompat.getColor(this, R.color.color_vertical_border_deep_red);
            int endColor = ContextCompat.getColor(this, R.color.color_vertical_border_light_red);


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

            leastSellingProductChart.setData(data);
        }
        leastSellingProductChart.invalidate();
    }




    private void initAdapter() {
        mAdapter = new TopCustomerListAdapter(getApplicationContext(), mArrayList, (view, position) -> {

        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCustomers.setLayoutManager(layoutManager);
        rvCustomers.setAdapter(mAdapter);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_inventory:
                startActivity(new Intent(MainActivity.this, InventoryActivity.class));
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.ll_customer:
                startActivity(new Intent(MainActivity.this, CustomerListActivity.class));
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.ll_cash_due:
                startActivity(new Intent(MainActivity.this, CashDueActivity.class));
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
    }


}
