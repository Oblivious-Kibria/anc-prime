package com.anc.ancprime.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.anc.ancprime.R;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class InventoryProductListAdapter extends RecyclerView.Adapter<InventoryProductListAdapter.ViewHolder> {



    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private List<String> mArrayList;

    private final String currencySign;
    private DecimalFormat formatter = new DecimalFormat("#,###,##0");




    public InventoryProductListAdapter(Context context, List<String> arrayList, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.mArrayList = arrayList;
        this.onItemClickListener = onItemClickListener;
        currencySign = context.getResources().getString(R.string.currency_sign);
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory_list, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }




    @Override
    public int getItemCount() {
        return mArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_product_name)
        AppCompatTextView tvProductName;
        @BindView(R.id.tv_unit_available)
        AppCompatTextView tvUnitAvailable;
        @BindView(R.id.ll_left)
        LinearLayout llLeft;
        @BindView(R.id.tv_price)
        AppCompatTextView tvPrice;
        @BindView(R.id.ll_right)
        LinearLayout llRight;
        @BindView(R.id.view_space)
        View viewSpace;
        @BindView(R.id.ll_row)
        LinearLayoutCompat llRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }




        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }


    }


    public interface OnItemClickListener {


        void onItemClick(View view, int position);


    }


}
