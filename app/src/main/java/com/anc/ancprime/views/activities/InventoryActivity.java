package com.anc.ancprime.views.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anc.ancprime.R;
import com.anc.ancprime.views.adapters.InventoryProductListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class InventoryActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.titleA)
    AppCompatTextView titleA;
    @BindView(R.id.titleB)
    AppCompatTextView titleB;
    @BindView(R.id.ll_tabs)
    LinearLayoutCompat llTabs;
    @BindView(R.id.view_border)
    View viewBorder;
    @BindView(R.id.tv_completed)
    AppCompatTextView tvCompleted;
    @BindView(R.id.tv_completed_text)
    AppCompatTextView tvCompletedText;
    @BindView(R.id.cl_completed)
    ConstraintLayout clCompleted;
    @BindView(R.id.tv_remaining)
    AppCompatTextView tvRemaining;
    @BindView(R.id.tv_remaining_text)
    AppCompatTextView tvRemainingText;
    @BindView(R.id.cl_remaining)
    ConstraintLayout clRemaining;
    @BindView(R.id.cl_dashboard)
    ConstraintLayout clDashboard;
    @BindView(R.id.view_border_1)
    View viewBorder1;
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;
    @BindView(R.id.iv_empty_icon)
    AppCompatImageView ivEmptyIcon;
    @BindView(R.id.tv_empty_text)
    AppCompatTextView tvEmptyText;
    @BindView(R.id.progress_circular)
    ContentLoadingProgressBar progressCircular;
    @BindView(R.id.include_esp_profile_layout)
    RelativeLayout includeEmptyLayout;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private InventoryProductListAdapter mAdapter;
    private List<String> mArrayList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);


        initView();
        initListeners();
    }




    private void initView() {
        setToolbar(toolbar);
        initAdapter();
    }




    private void initListeners() {
        titleA.setOnClickListener(this);
        titleB.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleA:
                controlTabs("WAREHOUSE");
                break;
            case R.id.titleB:
                controlTabs("FACTORY");
                break;
        }
    }




    private void controlTabs(String tab) {
        Typeface typefaceBold = ResourcesCompat.getFont(this, R.font.roboto_bold);
        Typeface typefaceLight = ResourcesCompat.getFont(this, R.font.roboto_light);

        if (tab.equalsIgnoreCase("WAREHOUSE")) {
            titleA.setTypeface(typefaceBold);
            titleB.setTypeface(typefaceLight);
            titleA.setTextColor(getResources().getColor(R.color.color_text_prime_lite));
            titleB.setTextColor(getResources().getColor(R.color.color_text_deep_gray_1));

        } else {
            titleA.setTypeface(typefaceLight);
            titleB.setTypeface(typefaceBold);

            titleA.setTextColor(getResources().getColor(R.color.color_text_deep_gray_1));
            titleB.setTextColor(getResources().getColor(R.color.color_text_prime_lite));
        }
    }




    private void initAdapter() {
        mArrayList = new ArrayList<>();
        mAdapter = new InventoryProductListAdapter(this, mArrayList, (view, position) -> {

        });
        rvProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvProducts.setNestedScrollingEnabled(true);
        rvProducts.setAdapter(mAdapter);
    }




    private void resetAdapter(String type) {
        if (type.equalsIgnoreCase("WAREHOUSE")) {

        } else {

        }
    }


}
