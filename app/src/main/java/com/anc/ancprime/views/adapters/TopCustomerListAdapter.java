package com.anc.ancprime.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.anc.ancprime.R;
import com.github.florent37.shapeofview.shapes.CircleView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class TopCustomerListAdapter extends RecyclerView.Adapter<TopCustomerListAdapter.ViewHolder> {



    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private List<Customer> mArrayList;

    private final String currencySign;
    private DecimalFormat formatter = new DecimalFormat("#,###,##0.00");



    public TopCustomerListAdapter(Context context, List<Customer> arrayList, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.mArrayList = arrayList;
        this.onItemClickListener = onItemClickListener;
        currencySign = context.getResources().getString(R.string.currency_sign);
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_customer, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(mArrayList.get(position).getName());
        holder.tvAddress.setText(mArrayList.get(position).getAddress());
        holder.tvPurchaseValue.setText(mArrayList.get(position).getPurchaseValue()+" "+currencySign);
    }




    @Override
    public int getItemCount() {
        return mArrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_name)
        AppCompatTextView tvName;
        @BindView(R.id.tv_address)
        AppCompatTextView tvAddress;
        @BindView(R.id.iv_profile_pic)
        AppCompatImageView ivProfilePic;
        @BindView(R.id.cv_profile_pic)
        CircleView cvProfilePic;
        @BindView(R.id.tv_purchase_value)
        AppCompatTextView tvPurchaseValue;




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
