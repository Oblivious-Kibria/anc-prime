package com.anc.ancprime.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.anc.ancprime.R;

import butterknife.BindView;
import butterknife.ButterKnife;




public class CustomerListActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_customers)
    RecyclerView rvCustomers;
    @BindView(R.id.iv_empty_icon)
    AppCompatImageView ivEmptyIcon;
    @BindView(R.id.tv_empty_text)
    AppCompatTextView tvEmptyText;
    @BindView(R.id.progress_circular)
    ContentLoadingProgressBar progressCircular;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setToolbar(toolbar);

    }

}
